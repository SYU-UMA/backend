package com.jobayour.repository;
//수정
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

    Qualification findByIdAndQualificationsNum(String id, int qualificationsnum);

    // 유저의 qual 삭제
    void deleteQualificationByIdAndQualificationsNum(String id, int qualificationsnum);
}
