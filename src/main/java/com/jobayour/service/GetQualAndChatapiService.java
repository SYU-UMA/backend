package com.jobayour.service;

import com.jobayour.dto.QualificationDTO;
import com.jobayour.dto.QuestionAndAnswerDTO;
import com.jobayour.entity.Interview;
import com.jobayour.entity.Qualification;
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


     public void test(){

         //db에서 id값을 통해 데이터를 가져오는 부분,수정해야함

         Qualification getData = qualificationService.findTopByIdOrderByQualificationsNumDesc("test");

         String combinedQuestions = "직무는 " + getData.getJob() + "이고 연차는 " + getData.getCareer()
                 + "이고 질문 수준은 최고5라고 하면 " + getData.getLevel() + "이고 자격요건은 " + getData.getRequirements() +
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

         // list객체가 총 5개이고 각각의 값이 비어있는지 확인, 아니면 다시 메소드 실행
         // interviewQuestions 리스트의 크기가 5개이고, 각 객체의 question과 answer가 비어 있지 않은지 확인
         if (interviewQuestions.size() == 5 && interviewQuestions.stream().allMatch(qa -> !qa.getQuestion().isEmpty() && !qa.getAnswer().isEmpty())) {

             for(int i = 0;i< interviewQuestions.size();i++){

            Interview interview = new Interview(getData.getQualificationsNumber()
                    ,"test",interviewQuestions.get(i).getQuestion(),interviewQuestions.get(i).getAnswer());

            interviewService.addInterview(interview);
          }
         } else {
             test();
         }

//         //기존의 db저장 코드
//        for(int i = 0;i< interviewQuestions.size();i++){
//
//            Interview interview = new Interview(getData.getQualificationsNumber()
//                    ,"test",interviewQuestions.get(i).getQuestion(),interviewQuestions.get(i).getAnswer());
//
//            interviewService.addInterview(interview);
//        }


         //컨트롤러에 질문답변 객체 전달, 기존의 List<QuestionAndAnswerDTO> 으로 리턴 할 때의 코드, 리턴값 없어서 삭제
//         return interviewQuestions;


     }

}
