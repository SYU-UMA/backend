package com.jobayour.controller;


import com.jobayour.dto.QuestionAndAnswerDTO;
import com.jobayour.dto.QuestionsDTO;
import com.jobayour.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chatgpt")                //Chatgpt 연동하는 클래스
public class ChatgptController {

    private final ChatService chatService;      //chatgpt api통신을 위한 클래스

    //챗gpt 질문 응답 받는 페이지
    @PostMapping
    public ResponseEntity<List<QuestionAndAnswerDTO>> test(@RequestBody QuestionsDTO questionsDTO) {

        //
        // quallist db조회해서 필요한 데이터 가져오기 -> 여기부분을 빠르게 만들고
        //



        String combinedQuestions = "직무는 " + questionsDTO.getJob() + "이고 연차는 " + questionsDTO.getCareer()
                + "이고 질문 수준은 최고5라고 하면 " + questionsDTO.getLevel() + "이고 자격요건은 " + questionsDTO.getRequirement() +
                "일 때 나올 수 있는 예상면접질문 5가지와 답변까지 알려줘.  각 답변마다 \\를 꼭 넣어서 구분해줘 그리고 " +
                "답변을 1. 당신의 개발 경력과 관련된 자바 개발 경험은 무엇인가요? \\개발 경력은 5년 이상으로 자바 개발 관련 사업 개발 및 상세 설계, " +
                "성능 분석 및 최적화, 시스템 운영과 관련된 경험이 있습니다. " +
                "이런식으로 예상질문마다 뒤에는 꼭 ? 로 끝나게해줘 한글로알려줘";

        String response = chatService.getChatResponse(combinedQuestions);

        List<QuestionAndAnswerDTO> interviewQuestions = new ArrayList<>();

        // \로 구분한부분 string 배열로 나누기
        String[] pairs = response.split("\\.");

        // 질문과 답변 나누기
        for (String pair : pairs) {
            String[] parts = pair.split("\\\\");
            if (parts.length >= 2) {
                String question = parts[0].trim();
                String answer = parts[1].trim();
                QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO(question, answer);
                interviewQuestions.add(questionAndAnswerDTO);
            }
        }



        // 로직 돌리고 그다음에 db저장


        return ResponseEntity.ok(interviewQuestions);   //프론트에 리턴
    }

}
