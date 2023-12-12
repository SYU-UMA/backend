package com.jobayour.modules.resumefunc.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResumeRepository extends JpaRepository<UserResume, String> {

    List<UserResume> findAllByUserId(String id);

    UserResume findByResumeNumAndUserId(int num, String id);
    UserResume findTopByUserId(String id);;

    void deleteUserResumeByResumeNumAndAndUserId(int num, String id);
}
