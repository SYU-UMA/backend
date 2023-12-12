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
    public List<UserResumeDTO> findAllUserResume(String userId) {
        List<UserResume> userResumeList = userResumeRepository.findAllByUserId(userId);
        List<UserResumeDTO> list = userResumeList.stream()
                .sorted(Comparator.comparing(UserResume::getUserId))
                .map(userResume -> modelMapper.map(userResume, UserResumeDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //개인 유저아이디와 번호로 해당하는 이력서 조회
    public UserResume findResumeByIdAndnum(String id, int resumeNum) {
        UserResume userResume = userResumeRepository.findByResumeNumAndUserId(resumeNum, id);
        return userResume;
    }

    // 유저 이력 추가
    public void addUserResume(UserResume userResumeinfo) {
        UserResume userResume = new UserResume();
        userResume.setUserId(userResumeinfo.getUserId());
        userResume.setType(userResumeinfo.getType());
        userResume.setResumeName(userResumeinfo.getResumeName());
        userResume.setIntroJob(userResumeinfo.getIntroJob());
        userResume.setIntroContents(userResumeinfo.getIntroContents());
        userResume.setCore1(userResumeinfo.getCore1());
        userResume.setCore2(userResumeinfo.getCore2());
        userResume.setCore3(userResumeinfo.getCore3());
        userResume.setCore4(userResumeinfo.getCore4());
        userResume.setCore5(userResumeinfo.getCore5());
        userResumeRepository.save(modelMapper.map(userResume, UserResume.class));
    }

    // 개인 유저 이력 수정
    @Transactional
    public void modifyUserResume(UserResume userResumeinfo) {
        UserResume userResume = userResumeRepository
                .findByResumeNumAndUserId(userResumeinfo.getResumeNum(), userResumeinfo.getUserId());
        userResume.setUserId(userResumeinfo.getUserId());
        userResume.setResumeNum(userResumeinfo.getResumeNum());
        userResume.setType(userResumeinfo.getType());
        userResume.setResumeName(userResumeinfo.getResumeName());
        userResume.setIntroJob(userResumeinfo.getIntroJob());
        userResume.setIntroContents(userResumeinfo.getIntroContents());
        userResume.setCore1(userResumeinfo.getCore1());
        userResume.setCore2(userResumeinfo.getCore2());
        userResume.setCore3(userResumeinfo.getCore3());
        userResume.setCore4(userResumeinfo.getCore4());
        userResume.setCore5(userResumeinfo.getCore5());
    }

    // 개인 유저 이력 삭제
    @Transactional
    public void deleteUserResume(UserResume userResume) {
        userResumeRepository.deleteUserResumeByResumeNumAndAndUserId(userResume.getResumeNum(), userResume.getUserId());
    }


    // 개인 최근 이력 넘버 조회
    public int findRecentNum(String userId) {
        UserResume userResume = userResumeRepository.findTopByUserId(userId);
        return userResume.getResumeNum();
    }
}

