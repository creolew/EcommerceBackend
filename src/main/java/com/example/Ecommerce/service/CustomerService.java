package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.customer.CustomerDto;
import com.example.Ecommerce.payload.user.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> saveCustomer(CustomerDto customerDto);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}
