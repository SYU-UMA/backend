package com.jobayour.modules.chatgpt;

import com.jobayour.modules.interview.Interview;
import com.jobayour.modules.interview.InterviewService;
import com.jobayour.modules.qualification.Qualification;
import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.qualification.QualificationService;
import com.jobayour.modules.resumefunc.career.CareerDTO;
import com.jobayour.modules.resumefunc.career.CareerService;

import com.jobayour.modules.resumefunc.myCareer.MyCareer;
import com.jobayour.modules.resumefunc.myCareer.MyCareerDTO;
import com.jobayour.modules.resumefunc.myCareer.MyCareerService;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasicService;
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

    private final ResumeBasicService resumeBasicService;        //resumeBasic 정보 가져오기 위한 서비스

    private final SkillService skillService;                    //스킬정보를 가져오기 위한 서비스

    private final MyCareerService myCareerService;              //지원서 내용 가져오기위한 서비스
    public void test(HttpServletRequest request,CandidateKeyDTO candidateKeyDTO){

        int checkNumber = 5;        // 5개의 질문과 답변을 저장하기위해 체크하는 정보

        String careerPrompt = "";       //career 정보를 담기위한 변수
        String skillPrompt="";          //skill 정보를 담기위한 변수
        String myCareerPrompt="";       //myCareer 정보를 담기위한 변수
        String resumeBasicPrompt="";    //resumeBasic 정보를 담기위한 변수

        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출

        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        // quallist db조회해서 필요한 데이터 가져오기
        Qualification getData = qualificationService.findTopByIdOrderByQualificationsNumDesc(userId);


        String combinedQuestions = "너는 이제 회사의 it분야 면접관이야."+"직무는 " + getData.getJob() + "이고 연차는 " + getData.getCareer()
                + "이고 질문 수준은 최고5라고 하면 " + getData.getLevel() + "이고 자격요건은 " + getData.getRequirements() +
                "일 때 너가 할 수 있는 예상면접질문 1가지와 답변까지 알려줘.  각 답변마다 \\를 꼭 넣어서 구분해줘 그리고 " +
                "답변을 Q. 당신의 개발 경력과 관련된 자바 개발 경험은 무엇인가요? \\A.개발 경력은 5년 이상으로 자바 개발 관련 사업 개발 및 상세 설계, " +
                "성능 분석 및 최적화, 시스템 운영과 관련된 경험이 있습니다. " +
                "이런식으로 예상질문 뒤에는 꼭 ? 로 끝나게해주고 한글로알려줘 그리고 현재 나에 대한 정보를 알려줄게. ";


        //경력 리스트 가져오기
        ResumeBasic resumeBasic1 = new ResumeBasic();
        resumeBasic1.setUserId(userId);
        resumeBasic1.setResumeNum(candidateKeyDTO.getResumeNum());

        //경력서 리스트
        List<CareerDTO> userCareerList = careerService.findCareerById(resumeBasic1.getUserId(), resumeBasic1.getResumeNum());

        //경력추가하기
        if(userCareerList.size()==0) {
           careerPrompt+="";
        }else{
            for (int i = 0; i < userCareerList.size(); i++) {
                careerPrompt += "내가 다녀본 회사는" + userCareerList.get(i).getCompanyName() + ",담당한 업무는"
                        + userCareerList.get(i).getContents() + ", 담당부서는"
                        + userCareerList.get(i).getDeptName() + "이야.";
            }
            combinedQuestions+=careerPrompt;
        }

        //스킬 리스트 가져오기
        List<SkillDTO> skillList = skillService.skillListbyIdAndUserId(resumeBasic1.getResumeNum(), resumeBasic1.getUserId());

        //스킬 추가하기
        String skill="";
        if(skillList.size()==0) {
            skillPrompt+="";
        }else{
            for (int i = 0; i < skillList.size(); i++) {
                skill += skillList.get(i).getSkill()+" ";
            }
            skillPrompt += "내가 가지고 있는 skill은 " + skill + "이야.";
            combinedQuestions+="내가 가지고 있는 skill을 알려줄게"+skillPrompt;
        }

        //이력서 가져오기( My Career 부분 이용!)
        MyCareerDTO myCareer = myCareerService.findMyCareer(userId, candidateKeyDTO.getResumeNum());

        //이력서 추가하기
        if(myCareer != null) {
            myCareerPrompt += "나의 직무는 " + myCareer.getJob() + "이고 나의 소개서는 "
                    + myCareer.getIntroduction() + "이고 내가 가지고 있는 역량은 " + myCareer.getCoreCompetence1()
                    + "," + myCareer.getCoreCompetence1() + "," + myCareer.getCoreCompetence2() + ","
                    + myCareer.getCoreCompetence3() + "," + myCareer.getCoreCompetence4() + ","
                    + myCareer.getCoreCompetence5() + "를 가지고 있어.";
            combinedQuestions+="나의 자기소개서에 대해 알려줄게." + myCareerPrompt;
        }else{
            myCareerPrompt+="";
        }

        //resumeBasic 내용 가져오기
        ResumeBasic resumeBasic = resumeBasicService.findResumeByIdAndnum(userId, candidateKeyDTO.getResumeNum());

        //resumeBasic 내용 추가하기
        if(resumeBasicPrompt != null) {
            resumeBasicPrompt += "현재 나의 개발자 연차는 " + resumeBasic.getCareer() + "의 연차를 가지고 있어.";
            combinedQuestions += resumeBasicPrompt;
        }else{
            resumeBasicPrompt+="";
        }

        combinedQuestions+="나의 정보를 가지고 질문과 답변을 알려줘";


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
                                , userId, questionAndAnswerDTO.getQuestion(), questionAndAnswerDTO.getAnswer());

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
