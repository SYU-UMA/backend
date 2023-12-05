package com.jobayour.modules.resumefunc.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResumeRepository extends JpaRepository<UserResume, String> {

    List<UserResume> findAllByUserId(String id);

    UserResume findByResumeNumAndUserId(int num, String id);

    void deleteUserResumeByResumeNumAndAndUserId(int num, String id);

    //최근 가장 최근 유저 이력 전체 조회
    UserResume findTopByUserIdOrderByResumeNumDesc(String id);


}
