package com.jobayour.modules.resumefunc.skill;

import com.jobayour.modules.resumefunc.resume.UserResume;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {
    private SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // 유저 이력에 있는 스킬 조회
    @GetMapping("/list")
    public List<SkillDTO> SkillList(@RequestBody UserResume userResume){
        List<SkillDTO> skillList = skillService.skillListbyIdAndUserId(userResume);
        return skillList;
    }

    // 유저 스킬 한개 추가
    @PostMapping("/add")
    public String skillAdd(@RequestBody Skill skill){
        if("".equals(skill.getSkill())){
            return "추가 안됨";
        }
        skillService.addSkill(skill);
        return "스킬 추가 완료";
    }

    // 스킬 수정 (아이디/스킬넘버/이력넘버)
    @PostMapping("/modify")
    public String SkillModify(@RequestBody Skill skill){
        skillService.modifySkill(skill);
        return "수정완료";
    }

    // 스킬 삭제 (아이디/스킬넘버/이력넘버)
    @DeleteMapping("/delete")
    public String SkillDelete(@RequestBody Skill skill){
        skillService.deleteSkill(skill);
        return "삭제완료";
    }

}
