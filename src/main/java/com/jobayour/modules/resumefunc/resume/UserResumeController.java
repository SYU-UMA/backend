package com.jobayour.modules.resumefunc.resume;


import com.jobayour.modules.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/resume")
public class UserResumeController {

    private UserResumeService userResumeservice;

    public UserResumeController(UserResumeService userResumeservice) {
        this.userResumeservice = userResumeservice;
    }

    // 개인 유저 이력 전체 조회 핸들러 메소드
    @GetMapping("/list")
    public List<UserResumeDTO> UserResumeList(@RequestBody User user){
        List<UserResumeDTO> userResumeList = userResumeservice.findAllUserResume(user);
        return userResumeList;
    }
    // 개인 유저 이력 제목들만 조회하는 핸들러 메소드
    @GetMapping("/list/name")
    public List<String> UserResumeNameList(@RequestBody User user){
        List<UserResumeDTO> userResumeList = userResumeservice.findAllUserResume(user);
        return userResumeList.stream()
                .map(UserResumeDTO::getResumeName)
                .collect(Collectors.toList());
    }

    // 개인 유저 이력 추가 핸들러 메소드
    @PostMapping("/add")
    public String UserResumeAdd(@RequestBody UserResume userResume){
        userResumeservice.addUserResume(userResume);
        return "유저 이력 추가 완료";
    }

    // 개인 유저 이력 수정 핸들러 메소드
    @PostMapping("/modify")
    public String UserResumeModify(@RequestBody UserResume userResume){
        userResumeservice.modifyUserResume(userResume);
        return "개인 유저 이력 수정 완료";
    }

    // 개인 유저 이력 삭제 핸들러 메소드(이력이 사용된 것들 다 같이 삭제)
    @DeleteMapping("/delete")
    public String UserResumeDelete(@RequestBody UserResume userResume){
        userResumeservice.deleteUserResume(userResume);
        return "개인 유저 이력삭제완료";
    }

}
