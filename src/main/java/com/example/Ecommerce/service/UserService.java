package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    List<UserDto> listAll();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(int userId, UserDto userDto);

    void deleteUser(int id);


}
