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

    @Column(name = "resumename")
    private String resumeName;
    @Column(name = "introjob")
    private String introJob;
    @Column(name = "introcontents")
    private String introContents;
    @Column(name = "core1")
    private String core1;
    @Column(name = "core2")
    private String core2;
    @Column(name = "core3")
    private String core3;
    @Column(name = "core4")
    private String core4;
    @Column(name = "core5")
    private String core5;

    public UserResume() {
    }

    public UserResume(int resumeNum, String userId, String type, String resumeName, String introJob, String introContents, String core1, String core2, String core3, String core4, String core5) {
        this.resumeNum = resumeNum;
        this.userId = userId;
        this.type = type;
        this.resumeName = resumeName;
        this.introJob = introJob;
        this.introContents = introContents;
        this.core1 = core1;
        this.core2 = core2;
        this.core3 = core3;
        this.core4 = core4;
        this.core5 = core5;
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

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getIntroJob() {
        return introJob;
    }

    public void setIntroJob(String introJob) {
        this.introJob = introJob;
    }

    public String getIntroContents() {
        return introContents;
    }

    public void setIntroContents(String introContents) {
        this.introContents = introContents;
    }

    public String getCore1() {
        return core1;
    }

    public void setCore1(String core1) {
        this.core1 = core1;
    }

    public String getCore2() {
        return core2;
    }

    public void setCore2(String core2) {
        this.core2 = core2;
    }

    public String getCore3() {
        return core3;
    }

    public void setCore3(String core3) {
        this.core3 = core3;
    }

    public String getCore4() {
        return core4;
    }

    public void setCore4(String core4) {
        this.core4 = core4;
    }

    public String getCore5() {
        return core5;
    }

    public void setCore5(String core5) {
        this.core5 = core5;
    }

    @Override
    public String toString() {
        return "UserResume{" +
                "resumeNum=" + resumeNum +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", resumeName='" + resumeName + '\'' +
                ", introJob='" + introJob + '\'' +
                ", introContents='" + introContents + '\'' +
                ", core1='" + core1 + '\'' +
                ", core2='" + core2 + '\'' +
                ", core3='" + core3 + '\'' +
                ", core4='" + core4 + '\'' +
                ", core5='" + core5 + '\'' +
                '}';
    }
}
