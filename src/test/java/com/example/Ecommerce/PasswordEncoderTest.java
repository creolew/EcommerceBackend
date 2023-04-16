package com.example.Ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void testEncoderPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "huy2023";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);

    }
}
