package com.jobayour.repository;

import com.jobayour.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, String> {

    Optional<Interview> findById(String id);

    void deleteByIdAndInterviewNumber(String id, int interviewNumber);
}
