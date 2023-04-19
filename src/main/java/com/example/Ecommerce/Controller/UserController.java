package com.example.Ecommerce.Controller;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.UserDto;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')" )
    @GetMapping("/users")
    public List<UserDto> listAll(){


        return userService.listAll();
    }


    @PutMapping("updateUser/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDto userDto){
        UserDto tempUser = userService.updateUser(id, userDto);

        ResponseMessage responseMessage = new ResponseMessage("Update user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteUser/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable(name = "id") Integer id){
        userService.deleteUser(id);
        ResponseMessage responseMessage = new ResponseMessage("Delete user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


}
