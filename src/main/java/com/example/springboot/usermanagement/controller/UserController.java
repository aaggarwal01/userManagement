package com.example.springboot.usermanagement.controller;

import com.example.springboot.usermanagement.entity.User;
import com.example.springboot.usermanagement.exception.UserErrorResponse;
import com.example.springboot.usermanagement.exception.UserNotFound;
import com.example.springboot.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")

public class UserController {
    private UserService userService;

    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user_id}")
    public Optional<User> getUserById(@PathVariable String user_id){
        Optional<User> tempUser = userService.findById(user_id);

        if(tempUser.isEmpty()) {
            throw new UserNotFound("User ID: " + user_id + " does not exist!");
        }
        return tempUser;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User tempUser) {
        Optional <User> theUser = userService.findById(tempUser.getEmail());

        if(theUser == null ){
            throw new RuntimeException("User already Existed! Consider Updating the user!");
        }

        User newUser = userService.save(tempUser);

        return newUser;

    }

    @DeleteMapping("/{user_id}")
    public String deleteUserById(@PathVariable String user_id) {
        Optional<User> tempUser = userService.findById(user_id);

        if(tempUser.isEmpty()){
            throw new UserNotFound("User is not available with id - " + user_id);
        }

        userService.deleteById(user_id);
        return "Deleted User with the id - " + user_id;
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFound exc){
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
