package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.EcommerceAPIException;
import com.example.Ecommerce.payload.LoginDto;
import com.example.Ecommerce.payload.user.RegisterDto;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.security.JwtTokenProvider;
import com.example.Ecommerce.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

   private AuthenticationManager authenticationManager;
   private UserRepository userRepository;
   private RoleRepository roleRepository;

   private PasswordEncoder passwordEncoder;

   private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);



        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new EcommerceAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new EcommerceAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = mapToEntity(registerDto);

        userRepository.save(user);
        return "User registered successfully";
    }


    private User mapToEntity(RegisterDto registerDto){

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setEnabled(registerDto.isEnabled());
        user.setPhotos(registerDto.getPhotos());
        user.setLastName(registerDto.getLastName());
        user.setFirstName(registerDto.getFirstName());

        Set<String> strRoles = registerDto.getRoles();

        for(String str : strRoles){
            Role role = roleRepository.findByName(str);
            if(role == null){
                throw new RuntimeException("Error: Role is not found.");
            }

            user.addRole(role);

        };
        return user;

    }
}
