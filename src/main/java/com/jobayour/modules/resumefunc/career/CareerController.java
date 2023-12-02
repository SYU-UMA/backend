package com.jobayour.modules.resumefunc.career;


import com.jobayour.modules.resumefunc.resume.UserResume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {

    private CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    // 개인 경력 전체 조회(이력서 하나에 해당하는) 핸들러 메소드
    @GetMapping("/list")
    public List<CareerDTO> UserCareerList(@RequestBody UserResume userResume){
        List<CareerDTO> UserCareerList = careerService.findCareerById(userResume);
        return UserCareerList;
    }
    // 개인 경력 한개 조회 핸들러 메소드
    @GetMapping("/list/num")
    public CareerDTO UserCareerListByNum(@RequestBody Career career){
        CareerDTO UserCareerList = careerService.findCareerByIdAndNum(career);
        return UserCareerList;
    }

    // 경력 추가 핸들러 메소드
    @PostMapping("/add")
    public String CareerAdd(@RequestBody Career career){
        careerService.addCareer(career);
        return "경력 추가 완료";
    }

    // 경력 수정 핸들러 메소드
    @PostMapping("/modify")
    public String CareerModify(@RequestBody Career career){
        careerService.modifyCareer(career);
        return "경력 수정 완료";
    }

    // 경력 삭제 메소드
    @DeleteMapping("/delete")
    public String CareerDelete(@RequestBody Career career){
        careerService.deleteCareer(career);
        return "경력 삭제 완료";
    }
}
