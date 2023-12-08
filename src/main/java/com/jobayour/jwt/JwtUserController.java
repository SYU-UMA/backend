package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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



    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        jwtuserService.logoutUser(userId);
        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam String userId) {
        try {
            Map<String, Object> userInfo = jwtuserService.getUserInfo(userId);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
