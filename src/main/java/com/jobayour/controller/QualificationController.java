package com.jobayour.controller;

import com.jobayour.dto.QualificationDTO;
import com.jobayour.entity.Qualification;
import com.jobayour.service.QualificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/qualification")
public class QualificationController {

    private QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }
    // 유저아이디로 증명조회
    @GetMapping("/list/{id}")
        public List<QualificationDTO> qualList(@PathVariable String id) {
            List<QualificationDTO> qualList = qualificationService.findqualById(id);
            return qualList;
    }

    // 유저 증명 추가
    @PostMapping("/add")
    public String QualAdd(@RequestBody Qualification qualification){
        qualificationService.addQual(qualification);
        return "추가완료";
    }

    // 유저 증명 수정
    @PostMapping("/modify")
    public String QualModify(@RequestBody Qualification qualification) {
        List<QualificationDTO> qualList = qualificationService.findqualById(qualification.getId());
        if(qualList.size() == 0) {
            return "수정 안됬어요";
        }

        qualificationService.modifyQual(qualification);
        return "수정 완료";
    }

    // 유저 증명 삭제 인터뷰에 참고되어있을떈 삭제 불가, 수정 필요
    @DeleteMapping("/delete")
    public String QualDelete(@RequestBody Qualification qualification){
        qualificationService.deleteQual(qualification);
        return "삭제 완료";
    }
}
