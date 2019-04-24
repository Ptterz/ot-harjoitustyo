package appen.domain;

import java.util.Objects;
/**
 * A class to handle exercises.
 * @author Pete
 * @version 1.0
 * @since 1.0
 */
public class Exercise {

    private String question;
    private String answer;
    private int level;
    
    /**
     * A constructor of an exercise variable when level is not known.
     * @param question Question
     * @param answer Answer
     */
    public Exercise(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.level = 0;
    }
    
    /**
     * A constructor of an exercise.
     * @param question Question
     * @param answer Answer
     * @param level Level of an exercise
     */
    public Exercise(String question, String answer, int level) {
        this.question = question;
        this.answer = answer;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.question);
        hash = 47 * hash + Objects.hashCode(this.answer);
        hash = 47 * hash + this.level;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exercise other = (Exercise) obj;
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }

}
