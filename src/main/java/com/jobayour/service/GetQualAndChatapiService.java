package com.jobayour.service;

import com.jobayour.dto.QualificationDTO;
import com.jobayour.dto.QuestionAndAnswerDTO;
import com.jobayour.entity.Interview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetQualAndChatapiService {

    private final InterviewService interviewService;    //db저장을 위한 의존성 주입

    private final QualificationService qualificationService;    //db조회를 위한 위존성 주입

    private final ChatService chatService;      //chatgpt api통신을 위한 클래스


     public List<QuestionAndAnswerDTO> test(){

         //db에서 id값을 통해 데이터를 가져오는 부분,수정해야함
         List<QualificationDTO> getData=qualificationService.findqualById("test");

         String combinedQuestions = "직무는 " + getData.get(0).getJob() + "이고 연차는 " + getData.get(0).getCareer()
                 + "이고 질문 수준은 최고5라고 하면 " + getData.get(0).getLevel() + "이고 자격요건은 " + getData.get(0).getRequirements() +
                 "일 때 나올 수 있는 예상면접질문 5가지와 답변까지 알려줘.  각 답변마다 \\를 꼭 넣어서 구분해줘 그리고 " +
                 "답변을 1. 당신의 개발 경력과 관련된 자바 개발 경험은 무엇인가요? \\개발 경력은 5년 이상으로 자바 개발 관련 사업 개발 및 상세 설계, " +
                 "성능 분석 및 최적화, 시스템 운영과 관련된 경험이 있습니다. " +
                 "이런식으로 예상질문마다 뒤에는 꼭 ? 로 끝나게해줘 한글로알려줘";


         //챗gpt서비스에 질문하고 리턴받기
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


         //db에 다시 질문과 답변을 저장하는 로직 (일단은 하드코딩으로 작성)
//         Interview interview0 = new Interview(getData.get(0).getQualificationsNum()
//                 ,"test",interviewQuestions.get(0).getQuestion(),interviewQuestions.get(0).getAnswer());
//
//         interviewService.addInterview(interview0);

        for(int i = 0;i<5;i++){
            Interview interview = new Interview(getData.get(0).getQualificationsNum()
                    ,"test",interviewQuestions.get(i).getQuestion(),interviewQuestions.get(i).getAnswer());

            interviewService.addInterview(interview);
        }










         //컨트롤러에 질문답변 객체 전달
         return interviewQuestions;

     }





}
