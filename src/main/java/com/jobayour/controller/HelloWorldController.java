package com.jobayour.controller;


import com.jobayour.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//통신 테스트용 컨트롤러
@RestController
public class HelloWorldController {


    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }







}
