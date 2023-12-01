package com.jobayour.jwt;

import com.jobayour.modules.user.UserDTO;
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
    public UserDTO.User registerUser(@RequestBody UserDTO.User user) {
        return jwtuserService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDTO.User user) {
        return jwtuserService.loginUser(user);
    }


    /*
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 클라이언트 토큰을 삭제 시
        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }*/



}
