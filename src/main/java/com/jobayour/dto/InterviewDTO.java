package com.jobayour.dto;


import java.io.Serializable;

public class InterviewDTO implements Serializable {

    private int interviewNumber;    //인터뷰번호 auto
    private int qualificationsNum;  //자격번호
    private String id;              //아이디
    private String interviewQuestion;   // 질문
    private String interviewAnswer;     // 답변

    public InterviewDTO() {
    }

    public InterviewDTO(int interviewNumber, int qualificationsNum, String id, String interviewQuestion, String interviewAnswer) {
        this.interviewNumber = interviewNumber;
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
        return "InterviewDTO{" +
                "interviewNumber=" + interviewNumber +
                ", qualificationsNum=" + qualificationsNum +
                ", id='" + id + '\'' +
                ", interviewQuestion='" + interviewQuestion + '\'' +
                ", interviewAnswer='" + interviewAnswer + '\'' +
                '}';
    }
}
