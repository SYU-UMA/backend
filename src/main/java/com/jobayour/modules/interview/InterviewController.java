package com.jobayour.modules.interview;

import com.jobayour.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/interview")
public class InterviewController {

    private InterviewService interviewService;

    private final JwtTokenProvider jwtTokenProvider;

    public InterviewController(InterviewService interviewService, JwtTokenProvider jwtTokenProvider) {
        this.interviewService = interviewService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 인터뷰 리스트 조회
    @GetMapping("/list")
    public List<InterviewDTO> interviewList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<InterviewDTO> interviewList = interviewService.findInterviewByUserId(userId);
        return interviewList;
    }

    // 최근 인터뷰 5개 리스트 조회 (인터뷰번호 오름차순으로 정렬후 5개 가져오기 방식)
    @GetMapping("/recentlist")
    public List<InterviewDTO> recentinterviewList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<InterviewDTO> interviewList = interviewService.findTop5ByUserIdOrderByInterviewNumberDesc(userId);
        return interviewList;
    }

    // 인터뷰 추가
    @PostMapping("/add")
    public String InterviewAdd(@RequestBody Interview interview,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        interview.setUserId(userId);
        interviewService.addInterview(interview);
        return "추가완료";
    }

    // 인터뷰 삭제
    @DeleteMapping("/delete")
    public String InterviewDelete(@RequestBody Interview interview,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        interview.setUserId(userId);
        interviewService.deleteInterview(interview);
        return "삭제완료";
    }
}
