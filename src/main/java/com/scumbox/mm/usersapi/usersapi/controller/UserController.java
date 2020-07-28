package com.scumbox.mm.usersapi.usersapi.controller;

import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.service.PublishService;
import com.scumbox.mm.usersapi.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private PublishService publishService;

    @Autowired
    public UserController(UserService userService, PublishService publishService) {
        this.userService = userService;
        this.publishService = publishService;
    }

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("/")
    public User addUser(@RequestBody User user) {

        User userRes = userService.addUser(user);

        publishService.notifyUsersCreated(userRes);

        return userRes;

    }

    @GetMapping("/findByUsername")
    public User findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}
