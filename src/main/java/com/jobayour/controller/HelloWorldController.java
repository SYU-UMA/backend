package com.jobayour.controller;

import com.jobayour.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }






}
