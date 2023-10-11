package com.jobayour.service;

import com.jobayour.dto.InterviewDTO;
import com.jobayour.entity.Interview;
import com.jobayour.repository.InterviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewService {

    private InterviewRepository interviewRepository;

    private final ModelMapper modelMapper;

    public InterviewService(InterviewRepository interviewRepository, ModelMapper modelMapper) {
        this.interviewRepository = interviewRepository;
        this.modelMapper = modelMapper;
    }


    // 인터뷰 리스트 조회
    public List<InterviewDTO> findById(String id) {
        Optional<Interview> interviewList = interviewRepository.findById(id);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNum))
                .map(Qualification -> modelMapper.map(interviewList, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 인터뷰 추가
    public void addInterview(Interview interview) {
        Interview interview1 = new Interview();
        interview1.setId(interview.getId());
        interview1.setInterviewAnswer(interview.getInterviewAnswer());
        interview1.setInterviewQuestion(interview.getInterviewQuestion());
        interview1.setQualificationsNum(interview.getQualificationsNum());
        interviewRepository.save(modelMapper.map(interview1, Interview.class));
    }


    // 인터뷰 삭제
    @Transactional
    public void deleteInterview(Interview interview) {
        interviewRepository.deleteByIdAndInterviewNumber(interview.getId(), interview.getInterviewNumber());
    }
}
