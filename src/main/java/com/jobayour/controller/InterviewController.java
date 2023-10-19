package com.jobayour.controller;

import com.jobayour.dto.InterviewDTO;
import com.jobayour.entity.Interview;
import com.jobayour.service.InterviewService;
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
        List<InterviewDTO> interviewList = interviewService.findInterviewById(id);
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
