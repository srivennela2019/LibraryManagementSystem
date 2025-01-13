package com.example.library.controller;

import com.example.library.dto.ResponseMessage;
import com.example.library.entity.User;
import com.example.library.service.UserService;
import com.example.library.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.emailExist(user.getEmail())){
            return ResponseEntity.ok("User already exists. Please use different email or reset your password");
        }
        if(userService.isUserValid(user)){
            user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }
        String error =  "Username, email and password are required";
        return ResponseEntity.badRequest().body(error);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        if(userService.isUserDetailsValid(user.getEmail(), user.getPassword())){
            if(userService.authenticate(user.getEmail(), user.getPassword())){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage("Login Success",true));
            };
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseMessage("Login Failure",false));

        }
        String error =  "Username, email and password are required";
        return ResponseEntity.badRequest().body(error);
    }
}