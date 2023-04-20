package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> listAll();

//    UserDto createUser(UserDto userDto);

    UserDto updateUser(int userId, UserDto userDto);

    void deleteUser(int id);


}
