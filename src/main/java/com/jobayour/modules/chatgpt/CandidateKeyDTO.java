package com.jobayour.modules.chatgpt;

public class CandidateKeyDTO {

    private int userCareerNum;
    private int resumeNum;
    private int skillNum;

    public CandidateKeyDTO() {
    }

    public CandidateKeyDTO(int userCareerNum, int resumeNum, int skillNum) {
        this.userCareerNum = userCareerNum;
        this.resumeNum = resumeNum;
        this.skillNum = skillNum;
    }

    public int getUserCareerNum() {
        return userCareerNum;
    }

    public void setUserCareerNum(int userCareerNum) {
        this.userCareerNum = userCareerNum;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public int getSkillNum() {
        return skillNum;
    }

    public void setSkillNum(int skillNum) {
        this.skillNum = skillNum;
    }

    @Override
    public String toString() {
        return "CandidateKeyDTO{" +
                "userCareerNum=" + userCareerNum +
                ", resumeNum=" + resumeNum +
                ", skillNum=" + skillNum +
                '}';
    }
}
