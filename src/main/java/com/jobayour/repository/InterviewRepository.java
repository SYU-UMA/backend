package com.jobayour.repository;
//수정
import com.jobayour.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, String> {

    List<Interview> findInterviewByUserId(String id);

    List<Interview> findTop5ByUserIdOrderByInterviewNumberDesc(String id);

    void deleteByUserIdAndInterviewNumber(String id, int interviewNumber);
}
