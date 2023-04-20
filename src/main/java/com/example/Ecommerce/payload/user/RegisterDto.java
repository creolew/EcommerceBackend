package com.example.Ecommerce.payload.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String photos;

    private boolean enabled;

    private Set<String> roles = new HashSet<>();
}
