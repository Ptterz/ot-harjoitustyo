
package appen.domain;

import java.util.Objects;

public class Exercise {
    private String question;
    private String answer;

    public Exercise(String question, String answer) {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Exercise)) {
            return false;
        }
        
        Exercise other = (Exercise) obj;
        return question.equals(other.question);
    }
    
}
