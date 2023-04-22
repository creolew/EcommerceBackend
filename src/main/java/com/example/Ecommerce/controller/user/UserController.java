package com.example.Ecommerce.controller.user;

import com.example.Ecommerce.payload.user.UserDto;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PreAuthorize("hasRole('Admin')")
    @PutMapping("updateUser/{id}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDto userDto){
        UserDto tempUser = userService.updateUser(id, userDto);

        ResponseMessage responseMessage = new ResponseMessage("Update user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable(name = "id") Integer id){
        userService.deleteUser(id);
        ResponseMessage responseMessage = new ResponseMessage("Delete user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


}
