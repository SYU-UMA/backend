package com.jobayour.modules.resumefunc.career;

import java.io.Serializable;
import java.sql.Date;

public class CareerDTO implements Serializable {

    private int userCareerNum;
    private String userId;
    private int resumeNum;
    private String company;
    private String working;
    private Date enterDt;
    private Date endDt;
    private String position;
    private String companyOpen;
    private String responseibilities;
    private String salary;
    private String area;

    public CareerDTO() {
    }

    public CareerDTO(int userCareerNum, String userId, int resumeNum, String company, String working, Date enterDt, Date endDt, String position, String companyOpen, String responseibilities, String salary, String area) {
        this.userCareerNum = userCareerNum;
        this.userId = userId;
        this.resumeNum = resumeNum;
        this.company = company;
        this.working = working;
        this.enterDt = enterDt;
        this.endDt = endDt;
        this.position = position;
        this.companyOpen = companyOpen;
        this.responseibilities = responseibilities;
        this.salary = salary;
        this.area = area;
    }

    public int getUserCareerNum() {
        return userCareerNum;
    }

    public void setUserCareerNum(int userCareerNum) {
        this.userCareerNum = userCareerNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public Date getEnterDt() {
        return enterDt;
    }

    public void setEnterDt(Date enterDt) {
        this.enterDt = enterDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyOpen() {
        return companyOpen;
    }

    public void setCompanyOpen(String companyOpen) {
        this.companyOpen = companyOpen;
    }

    public String getResponseibilities() {
        return responseibilities;
    }

    public void setResponseibilities(String responseibilities) {
        this.responseibilities = responseibilities;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "CareerDTO{" +
                "userCareerNum=" + userCareerNum +
                ", userId='" + userId + '\'' +
                ", resumeNum=" + resumeNum +
                ", company='" + company + '\'' +
                ", working='" + working + '\'' +
                ", enterDt=" + enterDt +
                ", endDt=" + endDt +
                ", position='" + position + '\'' +
                ", companyOpen='" + companyOpen + '\'' +
                ", responseibilities='" + responseibilities + '\'' +
                ", salary='" + salary + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
