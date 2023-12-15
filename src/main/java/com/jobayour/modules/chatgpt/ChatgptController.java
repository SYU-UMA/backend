package com.jobayour.modules.chatgpt;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


// ****** 하나의 질문과 답변만 가져오는 test용 컨트롤러 + 이력서 내용 첨가 (현재 세팅은 신입 관련 ) ******
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chatgpt")                //Chatgpt 연동하는 클래스, 이력서 정보를 포함하는 경우
public class ChatgptController {

    private final ChatgptNeedResumeService chatgptNeedResumeService;

    private final ChatgptNoResumeService chatgptNoResumeService;

    @PostMapping("/needresume")  //챗gpt 질문 응답 받는 컨트롤러(resumeNum 받아오는 방식)
    public void test(HttpServletRequest request, @RequestBody CandidateKeyDTO candidateKeyDTO) {
        chatgptNeedResumeService.test(request,candidateKeyDTO);
    }

    @PostMapping("/noresume")    //챗gpt 질문 응답 받는 컨트롤러(resumeNum 안받아오는 방식)
    public void test(HttpServletRequest request){
        chatgptNoResumeService.test(request);
    }

}
