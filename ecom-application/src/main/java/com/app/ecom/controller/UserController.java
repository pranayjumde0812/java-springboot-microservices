package com.app.ecom.controller;

import com.app.ecom.dtos.request.UserRequest;
import com.app.ecom.dtos.response.UserResponse;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {

        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User added Successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long userId) {
        boolean result = userService.updateUserById(userRequest, userId);
        if (result)
            return ResponseEntity.ok("User Updated Successfully.");
        return ResponseEntity.notFound().build();
    }
}
