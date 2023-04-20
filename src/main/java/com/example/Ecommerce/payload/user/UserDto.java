package com.example.Ecommerce.payload.user;

import com.example.Ecommerce.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Integer id;

    private String username;

    private String email;


    private String password;

    private String firstName;

    private String lastName;

    private String photos;

    private boolean enabled;

    private Set<String> roles = new HashSet<>();

}
