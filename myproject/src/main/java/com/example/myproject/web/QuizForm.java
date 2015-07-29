package com.example.myproject.web;

import java.io.Serializable;

public class QuizForm implements Serializable {
    private long quizId;
    private int current;
    private int total;

    public QuizForm(long quizId, int current, int total) {
        this.quizId = quizId;
        this.current = current;
        this.total = total;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "QuizForm{" +
                "quizId=" + quizId +
                ", current=" + current +
                ", total=" + total +
                '}';
    }
}
