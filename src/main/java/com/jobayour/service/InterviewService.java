package com.jobayour.service;

import com.jobayour.dto.InterviewDTO;
import com.jobayour.entity.Interview;
import com.jobayour.entity.Qualification;
import com.jobayour.repository.InterviewRepository;
import com.jobayour.repository.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//수정
@Service
public class InterviewService {

    private InterviewRepository interviewRepository;
    private QualificationRepository qualificationRepository;

    private final ModelMapper modelMapper;

    public InterviewService(InterviewRepository interviewRepository, QualificationRepository qualificationRepository,ModelMapper modelMapper) {
        this.interviewRepository = interviewRepository;
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
    }


    // 인터뷰 리스트 조회
    public List<InterviewDTO> findInterviewById(String id) {
        List<Interview> interviewList = interviewRepository.findInterviewById(id);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNum))
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 인터뷰 추가
    public void addInterview(Interview interview) {
        Interview interview1 = new Interview();
        interview1.setId(interview.getId());
        interview1.setInterviewAnswer(interview.getInterviewAnswer());
        interview1.setInterviewQuestion(interview.getInterviewQuestion());
        // 아이디에 맞는 최근 qualificationsNumber 세팅
//        Qualification qual = qualificationRepository.findTopByIdOrderByQualificationsNumDesc(interview.getId());
//        interview1.setQualificationsNum(qual.getQualificationsNum());
        interview1.setQualificationsNum(interview.getQualificationsNum());
        interviewRepository.save(modelMapper.map(interview1, Interview.class));
    }


    // 인터뷰 삭제
    @Transactional
    public void deleteInterview(Interview interview) {
        interviewRepository.deleteByIdAndInterviewNumber(interview.getId(), interview.getInterviewNumber());;
    }
}
