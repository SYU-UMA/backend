package com.jobayour.dto;


import java.util.List;

public class QuestionsDTO {

    private List<String> questions;


    private String job;       //직무
    private String career;       //연차
    private String level;       //난이도
    private String requirement;       //자격요건

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }


}
