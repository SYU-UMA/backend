package com.jobayour.modules.interview;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/interview")
public class InterviewController {

    private InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    // 인터뷰 리스트 조회
    @GetMapping("/list")
    public List<InterviewDTO> interviewList(@RequestParam String id){
        List<InterviewDTO> interviewList = interviewService.findInterviewByUserId(id);
        return interviewList;
    }

    // 최근 인터뷰 5개 리스트 조회 (인터뷰번호 오름차순으로 정렬후 5개 가져오기 방식)
    @GetMapping("/recentlist")
    public List<InterviewDTO> recentinterviewList(@RequestParam String id){
        List<InterviewDTO> interviewList = interviewService.findTop5ByUserIdOrderByInterviewNumberDesc(id);
        return interviewList;
    }

    // 인터뷰 추가
    @PostMapping("/add")
    public String InterviewAdd(@RequestBody Interview interview){
        interviewService.addInterview(interview);
        return "추가완료";
    }

    // 인터뷰 삭제
    @DeleteMapping("/delete")
    public String InterviewDelete(@RequestBody Interview interview){
        interviewService.deleteInterview(interview);
        return "삭제완료";
    }
}