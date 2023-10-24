package com.jobayour.dto;

import java.io.Serializable;
//수정
public class QualificationDTO implements Serializable {

    private int qualificationsNum;
    private String id;
    private String job;
    private String career;
    private String level;
    private String requirements;

    public QualificationDTO() {
    }

    public QualificationDTO(int qualificationsNum, String id, String job, String career, String level, String requirements) {
        this.qualificationsNum = qualificationsNum;
        this.id = id;
        this.job = job;
        this.career = career;
        this.level = level;
        this.requirements = requirements;
    }

    public int getQualificationsNum() {
        return qualificationsNum;
    }

    public void setQualificationsNum(int qualificationsNum) {
        this.qualificationsNum = qualificationsNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "QualificationDTO{" +
                "qualificationsNum=" + qualificationsNum +
                ", id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", career='" + career + '\'' +
                ", level='" + level + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}
