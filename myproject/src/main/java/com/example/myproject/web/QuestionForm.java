package com.example.myproject.web;

import com.example.myproject.web.dto.AnswerDTO;
import com.example.myproject.web.dto.QuestionDTO;

import java.io.Serializable;
import java.util.Set;

public class QuestionForm implements Serializable {
    public QuestionForm(QuizForm quizForm, QuestionDTO question, Set<AnswerDTO> answers) {
        this.quizForm = quizForm;
        this.question = question;
        this.answers = answers;
    }

    private QuizForm quizForm;

    private QuestionDTO question;

    private Set<AnswerDTO> answers;

    public QuizForm getQuizForm() {
        return quizForm;
    }

    public void setQuizForm(QuizForm quizForm) {
        this.quizForm = quizForm;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public Set<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDTO> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionForm{" +
                "questionForm=" + quizForm +
                ", question=" + question +
                ", answers=" + answers +
                '}';
    }
}
