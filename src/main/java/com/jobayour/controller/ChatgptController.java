package com.jobayour.controller;


import com.jobayour.dto.QuestionsDTO;
import com.jobayour.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chatgpt")                //Chatgpt 연동하는 클래스
public class ChatgptController {

    private final ChatService chatService;

    //챗gpt 질문 응답 받는 페이지
    @PostMapping()
    public List<String> test(@RequestBody QuestionsDTO questionsDTO){

        String combinedQuestions = "직무는" + questionsDTO.getJob() + "이고 연차는 " + questionsDTO.getCareer()
        + "이고 질문 수준은 최고5라고 하면 " + questionsDTO.getLevel() + "이고 자격요건은 " + questionsDTO.getRequirement() +
        "일 때 나올 수 있는 면접질문 3가지를 알려줘. 다른말 없이 질문만 알려주고 질문마다 \\로 구분해줘";
        String response = chatService.getChatResponse(combinedQuestions);
        List<String> responses = Arrays.asList(response.split("\\."));

        return responses;

    }

}
