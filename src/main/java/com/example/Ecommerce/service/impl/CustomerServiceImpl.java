package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.ConfirmationToken;
import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.customer.CustomerDto;
import com.example.Ecommerce.payload.user.RegisterDto;
import com.example.Ecommerce.repository.ConfirmationTokenRepository;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public ResponseEntity<?> saveCustomer(CustomerDto customerDto) {
        if (userRepository.existsByEmail(   customerDto.getEmail()   ) ){
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = mapToEntity(customerDto);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/api/customer/v1/confirm-account?token="
                + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");

    }


    private User mapToEntity(CustomerDto customerDto){

        User user = new User();

        user.setUsername(customerDto.getUsername());
        user.setEmail(customerDto.getEmail());

        user.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        user.setEnabled(false);
        user.setPhotos(null);

        user.setLastName(customerDto.getLastName());
        user.setFirstName(customerDto.getFirstName());

        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        user.addRole(role);

        return user;

    }
}
