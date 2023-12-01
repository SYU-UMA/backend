package com.jobayour.modules.resumefunc.resume;


import java.io.Serializable;

public class UserResumeDTO implements Serializable {

    private int resumeNum;
    private String userId;
    private String type;

    public UserResumeDTO() {
    }

    public UserResumeDTO(int resumeNum, String userId, String type) {
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
        return "UserResumeDTO{" +
                "resumeNum=" + resumeNum +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
