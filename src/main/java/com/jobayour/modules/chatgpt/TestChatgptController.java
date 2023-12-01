package com.jobayour.modules.chatgpt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/testChatgpt")                //Chatgpt 연동하는 테스트클래스
public class TestChatgptController {

    private final GetQualAndChatapiService getQualAndChatapiService;

    //db에서 저장한 데이터를 통해 chatgpt에 검색해서 리턴받는 메소드(
    @PostMapping
    public void test(){

        getQualAndChatapiService.test();

    }

}
