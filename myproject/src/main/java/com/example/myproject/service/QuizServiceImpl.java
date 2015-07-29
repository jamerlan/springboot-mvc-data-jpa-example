package com.example.myproject.service;

import com.example.myproject.domain.*;
import com.example.myproject.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    @Inject
    private QuizRepository quizRepository;

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private AnswerRepository answerRepository;

    @Override
    @Transactional
    public Quiz generateQuiz() {
        Quiz quiz = new Quiz();

        Set<Question> questionsToAnswer = new HashSet<>();

        long max = questionRepository.count() - 1;

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        while(questionsToAnswer.size() < Constants.QUIZ_QUESTIONS_COUNT) {
            long nextQuestionId = threadLocalRandom.nextLong(max) + 1;// exclude 0
            Question question = questionRepository.findOne(nextQuestionId);
            questionsToAnswer.add(question);
        }

        quiz.setQuestionsToAnswer(questionsToAnswer);

        quizRepository.save(quiz);

        return quiz;
    }

    @Override
    @Transactional
    public Quiz getQuiz(long quizId) {
        return quizRepository.findOne(quizId);
    }

    @Override
    @Transactional
    public Question getQuizQuestion(long quizId) {
        Quiz quiz = quizRepository.findOne(quizId);
        Question question = quiz.getQuestionsToAnswer().iterator().next();
        quiz.getQuestionsToAnswer().remove(question);
        quizRepository.save(quiz);
        return question;
    }

    @Override
    @Transactional
    public Set<Answer> getRandomAnswers() {
        long max = answerRepository.count() - 1;
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        long answersSize = threadLocalRandom.nextLong(max) + 1;// exclude 0

        Set<Answer> answers = new HashSet<>();

        while(answers.size() < answersSize) {
            long nextAnswerId = threadLocalRandom.nextLong(max) + 1;// exclude 0
            Answer answer = answerRepository.findOne(nextAnswerId);
            answers.add(answer);
        }

        return answers;
    }

    @Override
    @Transactional
    public void saveAnswer(long quizId, long questionId, long answerId) {
        Quiz quiz = quizRepository.findOne(quizId);
        Question question = questionRepository.findOne(questionId);
        if (question.getCorrectAnswer().getId() == answerId) {
            quiz.increaseScore();
            quizRepository.save(quiz);
        }
    }
}
