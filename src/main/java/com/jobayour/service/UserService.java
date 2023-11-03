package com.jobayour.service;

import com.jobayour.dto.UserDTO;
import com.jobayour.entity.User;
import com.jobayour.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
//수정
@Service
public class UserService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //유저 전체 리스트 조회
    public List<UserDTO> findAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> list = userList.stream()
                .sorted(Comparator.comparing(User::getUserId))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //유저 아이디 찾기
    public List<UserDTO> findUserById(String id){
        List<User> userList = userRepository.findUserByUserId(id);
        List<UserDTO> list = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //유저 추가
    public void addUser(String id, String pwd) {
        User user = new User();
        user.setUserId(id);
        user.setUserPwd(pwd);
        userRepository.save(modelMapper.map(user, User.class));
    }

    @Transactional
    public void modifyUser(String id, String pwd) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.setUserId(id);
        user.setUserPwd(pwd);
    }

    @Transactional
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
