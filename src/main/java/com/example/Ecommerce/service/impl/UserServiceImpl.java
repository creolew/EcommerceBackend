package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.payload.user.UserDto;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepository) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> listAll() {
        List<UserDto> listUsersDto = userRepo.findAll().stream().map(user -> mapToDto(user)).collect(Collectors.toList());

        return listUsersDto;
    }


    public UserDto updateUser(int userId, UserDto userDto){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if(userDto.getFirstName() != null){
            user.setFirstName(userDto.getFirstName());
        }

        if(userDto.getLastName() != null){
            user.setLastName(userDto.getLastName());
        }

        if(userDto.getUsername() != null){
            user.setUsername(userDto.getUsername());
        }

        if(userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());

        }

        if(userDto.getPhotos() != null){
            user.setPhotos(userDto.getPhotos());

        }

        if(userDto.getPassword() != null){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        }
        if(userDto.isEnabled()){
            user.setEnabled(userDto.isEnabled());

        }

        userRepo.save(user);

        return mapToDto(user);


    }

    public void deleteUser(int id){
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepo.delete(user);


    }


    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());
        userDto.setPhotos(user.getPhotos());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setRoles(Collections.singleton(user.getRoles().toString()));

        return userDto;
    }





}
