package com.jobayour.controller;

import com.jobayour.dto.QuestionAndAnswerDTO;

import com.jobayour.service.GetQualAndChatapiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/testChatgpt")                //Chatgpt 연동하는 테스트클래스
public class TestChatgptController {

    private final GetQualAndChatapiService getQualAndChatapiService;

    //db에서 저장한 데이터를 통해 chatgpt에 검색해서 리턴받는 메소드(
    @PostMapping
    public ResponseEntity<List<QuestionAndAnswerDTO>> test(){


        List<QuestionAndAnswerDTO> interviewQuestions  = getQualAndChatapiService.test();


        return ResponseEntity.ok(interviewQuestions);
    }

}
