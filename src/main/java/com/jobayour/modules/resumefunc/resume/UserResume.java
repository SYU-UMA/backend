package com.jobayour.modules.resumefunc.resume;

import javax.persistence.*;

/* 2023-12-01
*  이력서 엔티티*/
@Entity
@Table(name = "userresume")
public class UserResume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resumenum")
    private int resumeNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "type")
    private String type;

    public UserResume() {
    }

    public UserResume(int resumeNum, String userId, String type) {
        this.resumeNum = resumeNum;
        this.userId = userId;
        this.type = type;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserResume{" +
                "resumeNum=" + resumeNum +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
