package com.example.myproject.web;

import com.example.myproject.domain.Answer;
import com.example.myproject.domain.Question;

import java.io.Serializable;
import java.util.Set;

public class QuestionForm implements Serializable {
    public QuestionForm(QuizForm quizForm, Question question, Set<Answer> answers) {
        this.quizForm = quizForm;
        this.question = question;
        this.answers = answers;
    }

    private QuizForm quizForm;

    private Question question;

    private Set<Answer> answers;

    public QuizForm getQuizForm() {
        return quizForm;
    }

    public void setQuizForm(QuizForm quizForm) {
        this.quizForm = quizForm;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
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
