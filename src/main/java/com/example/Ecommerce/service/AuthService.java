package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.LoginDto;
import com.example.Ecommerce.payload.user.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
