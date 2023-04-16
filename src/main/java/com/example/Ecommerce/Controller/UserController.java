package com.example.Ecommerce.Controller;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.UserDto;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> listAll(){
         return userService.listAll();
    }

    @PostMapping("/saveUser")
    public ResponseEntity<ResponseMessage> saveUser(@RequestBody UserDto userDto) {

        UserDto tempUser = userService.createUser(userDto);

        ResponseMessage responseMessage = new ResponseMessage("Create user successfully", HttpStatus.CREATED.value());
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDto userDto){
        UserDto tempUser = userService.updateUser(id, userDto);

        ResponseMessage responseMessage = new ResponseMessage("Update user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable(name = "id") Integer id){
        userService.deleteUser(id);
        ResponseMessage responseMessage = new ResponseMessage("Delete user successfully", HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


}
