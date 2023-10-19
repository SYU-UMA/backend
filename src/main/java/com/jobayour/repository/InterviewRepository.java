package com.jobayour.repository;

import com.jobayour.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, String> {

    List<Interview> findInterviewById(String id);

    void deleteByIdAndInterviewNumber(String id, int interviewNumber);
}
