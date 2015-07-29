package com.example.myproject.service;

import com.example.myproject.domain.Answer;
import com.example.myproject.domain.Question;
import com.example.myproject.domain.Quiz;

import java.util.Set;

public interface QuizService {
    Quiz generateQuiz();

    Quiz getQuiz(long quizId);

    Question getQuizQuestion(long quizId);

    Set<Answer> getRandomAnswers();

    void saveAnswer(long quizId, long questionId, long answerId);
}
