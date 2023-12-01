package com.jobayour.modules.resumefunc.resume;

import com.jobayour.modules.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResumeService {

    private UserResumeRepository userResumeRepository;
    private final ModelMapper modelMapper;

    public UserResumeService(UserResumeRepository userResumeRepository, ModelMapper modelMapper) {
        this.userResumeRepository = userResumeRepository;
        this.modelMapper = modelMapper;
    }

    //개인 유저 이력 전체 조회
    public List<UserResumeDTO> findAllUserResume(User user) {
        List<UserResume> userResumeList = userResumeRepository.findAllByUserId(user.getUserId());
        List<UserResumeDTO> list = userResumeList.stream()
                .sorted(Comparator.comparing(UserResume::getUserId))
                .map(userResume -> modelMapper.map(userResume, UserResumeDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 유저 이력 추가
    public void addUserResume(UserResume userResumeinfo) {
        UserResume userResume1 = new UserResume();
        userResume1.setUserId(userResumeinfo.getUserId());
        userResume1.setType(userResumeinfo.getType());
        userResumeRepository.save(modelMapper.map(userResume1, UserResume.class));
    }

    // 개인 유저 이력 수정
    @Transactional
    public void modifyUserResume(UserResume userResumeinfo) {
        UserResume userResume = userResumeRepository
                .findByResumeNumAndUserId(userResumeinfo.getResumeNum(), userResumeinfo.getUserId());
        userResume.setUserId(userResume.getUserId());
        userResume.setResumeNum(userResume.getResumeNum());
        userResume.setType(userResumeinfo.getType());
    }

    // 개인 유저 이력 삭제
    @Transactional
    public void deleteUserResume(UserResume userResume) {
        userResumeRepository.deleteUserResumeByResumeNumAndAndUserId(userResume.getResumeNum(), userResume.getUserId());
    }
}

