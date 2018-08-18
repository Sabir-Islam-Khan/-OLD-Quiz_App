package com.asterisklab.quizquiz;

public class TrueFalse {

    private int questionID;
    private Boolean answer;

    public TrueFalse(int questionResource, Boolean trueOrFalse){

        questionID = questionResource;
        answer = trueOrFalse;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
