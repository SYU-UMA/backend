package com.jobayour.service;

import com.jobayour.dto.QualificationDTO;
import com.jobayour.entity.Qualification;
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
public class QualificationService {

    private QualificationRepository qualificationRepository;
    private final ModelMapper modelMapper;

    public QualificationService(QualificationRepository qualificationRepository, ModelMapper modelMapper) {
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
    }

    // 자격리스트 조회
    public List<QualificationDTO> findqualById(String id) {
        List<Qualification> qualList = qualificationRepository.findQualificationById(id);
        List<QualificationDTO> list = qualList.stream()
                .sorted(Comparator.comparing(Qualification::getQualificationsNum))
                .map(qualification -> modelMapper.map(qualification, QualificationDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 증명 추가
    public void addQual(Qualification qualification) {
        Qualification qual = new Qualification();
        qual.setId(qualification.getId());
        qual.setCarrer(qualification.getCarrer());
        qual.setJob(qualification.getJob());
        qual.setLevel(qualification.getLevel());
        qual.setRequirements(qualification.getRequirements());
        qualificationRepository.save(modelMapper.map(qual, Qualification.class));
    }

    // 증명 수정
    @Transactional
    public void modifyQual(Qualification qualification) {
        Qualification qual = qualificationRepository.findByIdAndQualificationsNum(qualification.getId(), qualification.getQualificationsNum());
        qual.setLevel(qualification.getLevel());
        qual.setCarrer(qualification.getCarrer());
        qual.setJob(qualification.getJob());
        qual.setRequirements(qualification.getRequirements());
    }

    // 증명 삭제
    @Transactional
    public void deleteQual(Qualification qualification) {
        qualificationRepository.deleteQualificationByIdAndQualificationsNum(qualification.getId(), qualification.getQualificationsNum());
    }


}
