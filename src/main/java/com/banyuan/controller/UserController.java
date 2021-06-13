package com.banyuan.controller;


import com.banyuan.bean.User;
import com.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public User login(@RequestBody User user){
        System.out.println(user);
        userService.login(user);
        return user;
    }
}
