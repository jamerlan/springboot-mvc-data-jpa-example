package com.example.myproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz implements Serializable {

    @Id
    @NotNull
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    @JoinTable(name="quiz_questions",
            joinColumns=@JoinColumn(name="quiz_id"),
            inverseJoinColumns=@JoinColumn(name="question_id"))
    private Set<Question> questionsToAnswer = new HashSet<>();

    @NotNull
    @Column(name = "score", nullable = false)
    private int score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Question> getQuestionsToAnswer() {
        return questionsToAnswer;
    }

    public void setQuestionsToAnswer(Set<Question> questionsToAnswer) {
        this.questionsToAnswer = questionsToAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        score = score + 1;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", questionsToAnswer=" + questionsToAnswer +
                ", score=" + score +
                '}';
    }
}
