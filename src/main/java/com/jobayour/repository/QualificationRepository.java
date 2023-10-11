package com.jobayour.repository;

import com.jobayour.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {

    // 유저의 qual 리스트 조회
    List<Qualification> findById(String id);

    // 유저의 qual 삭제
    void deleteQualificationByIdAndAndQualificationsNum(String id, int qualificationsNum);
}
