package com.jobayour.modules.chatgpt;

import com.jobayour.modules.interview.Interview;
import com.jobayour.modules.interview.InterviewService;
import com.jobayour.modules.qualification.Qualification;
import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.qualification.QualificationService;
import com.jobayour.modules.resumefunc.career.Career;
import com.jobayour.modules.resumefunc.career.CareerDTO;
import com.jobayour.modules.resumefunc.career.CareerService;
import com.jobayour.modules.resumefunc.resume.UserResume;
import com.jobayour.modules.resumefunc.resume.UserResumeService;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasicService;
import com.jobayour.modules.resumefunc.skill.Skill;
import com.jobayour.modules.resumefunc.skill.SkillDTO;
import com.jobayour.modules.resumefunc.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatgptSearchService {

    private final InterviewService interviewService;    //db저장을 위한 의존성 주입

    private final ChatService chatService;      //chatgpt api통신을 위한 클래스

    private final QualificationService qualificationService;    //db조회를 위한 위존성 주입

    private final JwtTokenProvider jwtTokenProvider;            //jwt 토큰 정보를 받기위한 Provider

    private final  CareerService careerService;                 //이력서 정보를 가져오기위한 서비스

    private final ResumeBasic resumeBasic;          //자소서 정보를 가져오기위한 서비스
    private final ResumeBasicService resumeBasicService;

    private final SkillService skillService;
    public void test(HttpServletRequest request,CandidateKeyDTO candidateKeyDTO){       //***프론트에서 resumeNum과 userCareerNum을 매개변수로 받아와야 할듯***

        int checkNumber = 5;        // 5개의 질문과 답변을 저장하기위해 체크하는 정보

        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출

        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        //경력 리스트 가져오기
        ResumeBasic resumeBasic1 = new ResumeBasic();
        resumeBasic1.setUserId(userId);
        resumeBasic1.setResumeNum(candidateKeyDTO.getResumeNum());
        List<CareerDTO> userCareerList = careerService.findCareerById(resumeBasic1);  //경력서 리스트

        //경력 리스트 여러개 조회되는지 테스트 (회사이름으로 체크)
        for(int i = 0;i<userCareerList.size();i++){
            System.out.println("다녀본 회사 : "+userCareerList.get(i).getCompanyName());
        }

        //스킬 리스트 가져오기
        List<SkillDTO> skillList = skillService.skillListbyIdAndUserId(resumeBasic);

        //스킬 내용 여러가지 가져와서 체크하기
        for(int i = 0;i<skillList.size();i++){
            System.out.println("가지고 있는 skill : " + skillList.get(i).getSkill());
        }

        //이력서 가져오기( My Career 부분 이용!)

        // 가져온 이력서 내용 체크하기




        // quallist db조회해서 필요한 데이터 가져오기
        Qualification getData = qualificationService.findTopByIdOrderByQualificationsNumDesc(userId);


        String combinedQuestions = "너는 이제 회사의 it분야 면접관이야."+"직무는 " + getData.getJob() + "이고 연차는 " + getData.getCareer()
                + "이고 질문 수준은 최고5라고 하면 " + getData.getLevel() + "이고 자격요건은 " + getData.getRequirements() +
                "일 때 너가 할 수 있는 예상면접질문 1가지와 답변까지 알려줘.  각 답변마다 \\를 꼭 넣어서 구분해줘 그리고 " +
                "답변을 Q. 당신의 개발 경력과 관련된 자바 개발 경험은 무엇인가요? \\A.개발 경력은 5년 이상으로 자바 개발 관련 사업 개발 및 상세 설계, " +
                "성능 분석 및 최적화, 시스템 운영과 관련된 경험이 있습니다. " +
                "이런식으로 예상질문 뒤에는 꼭 ? 로 끝나게해주고 한글로알려줘";

        while(checkNumber>0){

            String response = chatService.getChatResponse(combinedQuestions);

            String[] pairs = response.split("\\?");

            if(pairs.length >= 2) {
                String question = pairs[0].trim();
                String answer = pairs[1].trim();
                answer = answer.replaceAll("\\\\", "");
                if (question.startsWith("Q") && answer.startsWith("A")) {
                    QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO(question, answer);

                    //question과 answer 비어있지 않은지에 대한 여부 확인 후 저장
                    if (questionAndAnswerDTO.isQuestionAndAnswerValid()) {

                        Interview interview = new Interview(getData.getQualificationsNumber()
                                , "test", questionAndAnswerDTO.getQuestion(), questionAndAnswerDTO.getAnswer());

                        interviewService.addInterview(interview);

                        checkNumber--;
                    }else{
                        break;
                    }
                }
            }
        }

    }

}
