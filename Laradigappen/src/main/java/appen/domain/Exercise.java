package appen.domain;

import java.util.Objects;

/**
 * A class to handle exercises.
 *
 * @author Pete
 * @version 1.1
 * @since 1.0
 */
public class Exercise {

    private int id;
    private String question;
    private String answer;
    private int level;

    /**
     * A constructor of an exercise variable when level is not known.
     * <p>
     * This constructor is only used for testing. </p>
     *
     * @param question Question
     * @param answer Answer
     * @since 1.0
     */
    public Exercise(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.level = 0;
    }

    /**
     * A constructor of an exercise.
     *
     * @param question Question
     * @param answer Answer
     * @param level Level of an exercise
     * @since 1.0
     */
    public Exercise(String question, String answer, int level) {
        this.question = question;
        this.answer = answer;
        this.level = level;
    }

    /**
     * A constructor of an exercise.
     * <p>
     * This one is used when reading from the database. </p>
     *
     * @param id The id of an exercise
     * @param question Question
     * @param answer Answer
     * @param level Level of an exercise
     * @since 1.1
     */
    public Exercise(int id, String question, String answer, int level) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.question);
        hash = 79 * hash + Objects.hashCode(this.answer);
        hash = 79 * hash + this.level;
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
        if (!this.question.equals(other.question)) {
            return false;
        }
        return true;
    }

}
