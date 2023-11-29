package com.jobayour.jwt;

import com.jobayour.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class JwtUserController {

    @Autowired
    private JwtUserService jwtuserService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return jwtuserService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return jwtuserService.loginUser(user);
    }
}
