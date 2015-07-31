package com.example.myproject.web.dto;

import com.example.myproject.domain.Answer;

import java.io.Serializable;

public class AnswerDTO implements Serializable {
    private final long id;
    private final String answerText;

    public AnswerDTO(long id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public static AnswerDTO fromAnswer(Answer answer) {
        return new AnswerDTO(answer.getId(), answer.getAnswerText());
    }

    public long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }
}
