package com.jobayour.repository;

import com.jobayour.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, String> {

    // 유저의 qual 리스트
    //@Query("select q from Qualification q where q.id = ?1")
    List<Qualification> findQualificationById(String id);

    // 유저의 qual 삭제
    void deleteQualificationByIdAndAndQualificationsNum(String id, int qualificationsNum);
}
