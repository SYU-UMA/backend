package com.jobayour.modules.resumefunc.resume;


import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.user.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/resume")
public class UserResumeController {

    private UserResumeService userResumeservice;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResumeController(UserResumeService userResumeservice, JwtTokenProvider jwtTokenProvider) {
        this.userResumeservice = userResumeservice;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    // 개인 유저 이력 전체 조회 핸들러 메소드
    @GetMapping("/list")
    public List<UserResumeDTO> UserResumeList(@RequestBody User user, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        user.setUserId(userId);
        List<UserResumeDTO> userResumeList = userResumeservice.findAllUserResume(user);
        return userResumeList;
    }

    // 개인 유저 이력 한개 조회
    @GetMapping("/list/num")
    public UserResume UserResumeNum(HttpServletRequest request, @RequestParam int resumeNum){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        UserResume userResume = userResumeservice.findResumeByIdAndnum(userId, resumeNum);
        return userResume;
    }
    // 개인 유저 이력 제목들만 조회하는 핸들러 메소드
    @GetMapping("/list/name")
    public List<String> UserResumeNameList(@RequestBody User user,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        user.setUserId(userId);
        List<UserResumeDTO> userResumeList = userResumeservice.findAllUserResume(user);
        return userResumeList.stream()
                .map(UserResumeDTO::getResumeName)
                .collect(Collectors.toList());
    }

    // 개인 유저 이력 추가 핸들러 메소드
    @PostMapping("/add")
    public String UserResumeAdd(@RequestBody UserResume userResume,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        userResume.setUserId(userId);
        userResumeservice.addUserResume(userResume);
        return "유저 이력 추가 완료";
    }

    // 개인 유저 이력 수정 핸들러 메소드
    @PostMapping("/modify")
    public String UserResumeModify(@RequestBody UserResume userResume,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        userResume.setUserId(userId);
        userResumeservice.modifyUserResume(userResume);
        return "개인 유저 이력 수정 완료";
    }

    // 개인 유저 이력 삭제 핸들러 메소드(이력이 사용된 것들 다 같이 삭제)
    @DeleteMapping("/delete")
    public String UserResumeDelete(@RequestBody UserResume userResume,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        userResume.setUserId(userId);
        userResumeservice.deleteUserResume(userResume);
        return "개인 유저 이력삭제완료";
    }

}
