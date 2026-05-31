package com.techcrack.todoApi.controller;

import com.techcrack.todoApi.model.User;
import com.techcrack.todoApi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String verifyUser(@RequestBody User user) {
        return service.verify(user);
    }
}
