package com.example.myproject.web.dto;

import com.example.myproject.domain.Question;

import java.io.Serializable;

public class QuestionDTO implements Serializable {
    private final Long id;
    private final String questionText;

    public QuestionDTO(Long id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public static QuestionDTO fromQuestion(Question question) {
        return new QuestionDTO(question.getId(), question.getQuestionText());
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }
}
