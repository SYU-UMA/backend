package com.jobayour.dto;

public class QuestionAndAnswerDTO {

    private String question;        //질문
    private String answer;          //답변

    public QuestionAndAnswerDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
