package com.example.Ecommerce.payload.customer;

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
public class CustomerDto {
    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

//    private String photos;

//    private boolean enabled;

//    private String roles;
}
