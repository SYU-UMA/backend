package com.jobayour.entity;

import javax.persistence.*;

@Entity
@Table(name = "interview")
public class Interview {
    //수정
    @Id
    @Column(name = "interviewnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewNumber;
    @Column(name = "qualificationsnumber")
    private int qualificationsNum;
    @Column(name = "userid")
    private String id;
    @Column(name = "interviewquestion")
    private String interviewQuestion;
    @Column(name = "interviewanswer")
    private String interviewAnswer;

    public Interview() {
    }

    public Interview(int interviewNumber, int qualificationsNum, String id, String interviewQuestion, String interviewAnswer) {
        this.interviewNumber = interviewNumber;
        this.qualificationsNum = qualificationsNum;
        this.id = id;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    public Interview(int qualificationsNum, String id, String interviewQuestion, String interviewAnswer) {
        this.qualificationsNum = qualificationsNum;
        this.id = id;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    public int getInterviewNumber() {
        return interviewNumber;
    }

    public void setInterviewNumber(int interviewNumber) {
        this.interviewNumber = interviewNumber;
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

    public String getInterviewQuestion() {
        return interviewQuestion;
    }

    public void setInterviewQuestion(String interviewQuestion) {
        this.interviewQuestion = interviewQuestion;
    }

    public String getInterviewAnswer() {
        return interviewAnswer;
    }

    public void setInterviewAnswer(String interviewAnswer) {
        this.interviewAnswer = interviewAnswer;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewNumber=" + interviewNumber +
                ", qualificationsNum=" + qualificationsNum +
                ", id='" + id + '\'' +
                ", interviewQuestion='" + interviewQuestion + '\'' +
                ", interviewAnswer='" + interviewAnswer + '\'' +
                '}';
    }
}
