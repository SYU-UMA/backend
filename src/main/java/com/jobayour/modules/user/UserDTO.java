package com.jobayour.modules.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
//수정
public class UserDTO implements Serializable {

    private String userId;
    private String userPwd;

    public UserDTO() {
    }

    public UserDTO(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    //수정
    @Entity
    @Table(name="userprofiles")
    public static class User{
        @Id
        @Column(name = "userid")
        private String userId;

        @Column(name = "userpwd")
        private String userPwd;

        public User() {
        }

        public User(String userId, String userPwd) {
            this.userId = userId;
            this.userPwd = userPwd;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId='" + userId + '\'' +
                    ", userPwd='" + userPwd + '\'' +
                    '}';
        }
    }
}
