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
    public void test(){


        List<QuestionAndAnswerDTO> interviewQuestions  = getQualAndChatapiService.test();

        //db에 질문과 객체 다시 저장하는 코드 삽입 할 부분 -> 이거도 서비스에서 처리

    }

}
