package com.jobayour.modules.user;


import org.springframework.web.bind.annotation.*;

import java.util.List;
//수정
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 리스트 조회 핸들러 메소드
    @GetMapping("/list")
    public List<UserDTO> UserList(){
        List<UserDTO> userList = userService.findAllUser();
        return userList;
    }
    //유저 아이디 조회 핸들러 메소드
    @GetMapping("/{id}")
    public List<UserDTO> UserFind(@PathVariable String id){
        List<UserDTO> userList = userService.findUserById(id);
        //model.addAttribute("userList", userList); // model이 필요없나?
        return userList;
    }

    // 유저 추가 핸들러 메소드
    @PostMapping("/add")
    public String UserAdd(@RequestBody UserDTO.User user){
        List<UserDTO> userList = userService.findUserById(user.getUserId());
        if(userList.size() > 0){
            return "문제가있어요"; // if조건에 맞으면 돌아가는 출력
        }
        userService.addUser(user.getUserId(), user.getUserPwd());
        return "추가완료";
    }

    // 유저 비밀번호 수정 핸들러 메소드
    @PostMapping("/modify")
    public String UserModfiy(@RequestBody UserDTO.User user){
        List<UserDTO> userList = userService.findUserById(user.getUserId());
        if(userList.size() == 0) {
            return "수정 안됬어요";
        }

        userService.modifyUser(user.getUserId(), user.getUserPwd());
        return "수정완료";
    }

    // 유저 삭제 핸들러 메소드
    @DeleteMapping("/delete/{id}")
    public String UserDelete(@PathVariable String id) {
        userService.deleteUser(id);
        return "삭제완료";
    }
}
