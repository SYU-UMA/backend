package com.jobayour.entity;

import javax.persistence.*;
//수정
@Entity
@Table(name="qualifications")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualificationsnumber")
    private int qualificationsNum;
    @Column(name = "userid")
    private String id;
    @Column(name = "job")
    private String job;
    @Column(name = "career")
    private String career;
    @Column(name = "level")
    private String level;
    @Column(name = "requirements")
    private String requirements;

    public Qualification() {
    }

    public Qualification(int qualificationsNum, String id, String job, String career, String level, String requirements) {
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

    public void setCareer(String carrer) {
        this.career = carrer;
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
        return "Qualification{" +
                "qualificationsNum=" + qualificationsNum +
                ", id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", career='" + career + '\'' +
                ", level='" + level + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}
