package com.jobayour.modules.interview;


import java.io.Serializable;
//수정
public class InterviewDTO implements Serializable {

    private int interviewNumber;    //인터뷰번호 auto
    private int qualificationsNumber;  //자격번호
    private String userId;              //아이디
    private String interviewQuestion;   // 질문
    private String interviewAnswer;     // 답변

    public InterviewDTO() {
    }

    public InterviewDTO(int interviewNumber, int qualificationsNumber, String userId, String interviewQuestion, String interviewAnswer) {
        this.interviewNumber = interviewNumber;
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }


    public InterviewDTO(int qualificationsNumber, String userId, String interviewQuestion, String interviewAnswer) {
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    public int getInterviewNumber() {
        return interviewNumber;
    }

    public void setInterviewNumber(int interviewNumber) {
        this.interviewNumber = interviewNumber;
    }

    public int getQualificationsNumber() {
        return qualificationsNumber;
    }

    public void setQualificationsNumber(int qualificationsNumber) {
        this.qualificationsNumber = qualificationsNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", qualificationsNumber=" + qualificationsNumber +
                ", userId='" + userId + '\'' +
                ", interviewQuestion='" + interviewQuestion + '\'' +
                ", interviewAnswer='" + interviewAnswer + '\'' +
                '}';
    }
}
