package com.jobayour.jwt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTestController {

    @PostMapping("/test")
    public String test(){

        return "jwt 테스트 완료";
    }
}