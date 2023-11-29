package com.jobayour.controller;


import com.jobayour.dto.QuestionAndAnswerDTO;
import com.jobayour.dto.QuestionsDTO;
import com.jobayour.entity.Interview;
import com.jobayour.entity.Qualification;
import com.jobayour.service.ChatService;
import com.jobayour.service.InterviewService;
import com.jobayour.service.QualificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



// ****** 하나의 질문과 답변만 가져오는 test용 컨트롤러 + 이력서 내용 첨가 (현재 세팅은 신입 관련 ) ******
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chatgpt")                //Chatgpt 연동하는 클래스
public class ChatgptController {

    private final InterviewService interviewService;    //db저장을 위한 의존성 주입

    private final ChatService chatService;      //chatgpt api통신을 위한 클래스

    private final QualificationService qualificationService;    //db조회를 위한 위존성 주입



    //챗gpt 질문 응답 받는 페이지
    @PostMapping
    public void test() {

        int checkNumber = 5;

        // quallist db조회해서 필요한 데이터 가져오기
        Qualification getData = qualificationService.findTopByIdOrderByQualificationsNumDesc("test");


        while(checkNumber>0){

            String combinedQuestions = "직무는 " + getData.getJob() + "이고 연차는 " + getData.getCareer()
                        + "이고 질문 수준은 최고5라고 하면 " + getData.getLevel() + "이고 자격요건은 " + getData.getRequirements() +
                        "일 때 나올 수 있는 예상면접질문 1가지와 답변까지 알려줘.  각 답변마다 \\를 꼭 넣어서 구분해줘 그리고 " +
                        "답변을 Q. 당신의 개발 경력과 관련된 자바 개발 경험은 무엇인가요? \\A.개발 경력은 5년 이상으로 자바 개발 관련 사업 개발 및 상세 설계, " +
                        "성능 분석 및 최적화, 시스템 운영과 관련된 경험이 있습니다. " +
                        "이런식으로 예상질문 뒤에는 꼭 ? 로 끝나게해주고 한글로알려줘";

            String response = chatService.getChatResponse(combinedQuestions);

            String[] pairs = response.split("\\?");

            String question = pairs[0].trim();
            String answer = pairs[1].trim();
            answer = answer.replaceAll("\\\\", "");
            QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO(question, answer);

            //question과 answer 비어있지 않은지에 대한 여부 확인 후 저장
             if (questionAndAnswerDTO.isQuestionAndAnswerValid()) {

                Interview interview = new Interview(getData.getQualificationsNumber()
                         ,"test",questionAndAnswerDTO.getQuestion(),questionAndAnswerDTO.getAnswer());

                 interviewService.addInterview(interview);

                 checkNumber--;
            }

        }



    }

}
