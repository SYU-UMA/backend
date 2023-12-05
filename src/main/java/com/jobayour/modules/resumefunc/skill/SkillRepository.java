package com.jobayour.modules.resumefunc.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

    List<Skill> findAllByUserIdAndResumeNum(String id, int num);

    Skill findBySkillNumAndUserIdAndResumeNum(int skillNum, String id, int resumeNum);

    void deleteBySkillNumAndUserIdAndResumeNum(int skillNum, String id, int resumeNum);

    //최근 가장 최근 유저 skill 전체 조회
    Skill findTopByUserIdOrderBySkillNumDesc(String id);
}
