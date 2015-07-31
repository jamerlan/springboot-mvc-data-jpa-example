package com.example.myproject.service;

import com.example.myproject.domain.*;
import com.example.myproject.utils.Constants;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    private QuizRepository quizRepository;

    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    private RandomNumberService randomNumberService;

    @Override
    @Transactional
    public Quiz generateQuiz() {
        Quiz quiz = new Quiz();

        long max = questionRepository.count() - 1;

        Set<Long> questionsToAnswerIds = new HashSet<>();

        while(questionsToAnswerIds.size() < Constants.QUIZ_QUESTIONS_COUNT) {
            long nextQuestionId = randomNumberService.getNextLong(max) + 1;// exclude 0
            questionsToAnswerIds.add(nextQuestionId);
        }

        Set<Question> questionsToAnswer = new HashSet<>();
        questionsToAnswer.addAll(questionRepository.findAll(questionsToAnswerIds));

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
        Hibernate.initialize(question.getCorrectAnswer());
        quizRepository.save(quiz);
        return question;
    }

    @Override
    @Transactional
    public Set<Answer> getRandomAnswers() {
        long max = answerRepository.count() - 1;
        long answersSize = randomNumberService.getNextLong(max) + 1;// exclude 0

        Set<Long> answerIds = new HashSet<>();

        while(answerIds.size() < answersSize) {
            long nextAnswerId = randomNumberService.getNextLong(max) + 1;// exclude 0
            answerIds.add(nextAnswerId);
        }

        Set<Answer> answers = new HashSet<>();
        answers.addAll(answerRepository.findAll(answerIds));

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

    @Inject
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Inject
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Inject
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Inject
    public void setRandomNumberService(RandomNumberService randomNumberService) {
        this.randomNumberService = randomNumberService;
    }

    public static Logger getLogger() {
        return logger;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }

    public AnswerRepository getAnswerRepository() {
        return answerRepository;
    }

    public RandomNumberService getRandomNumberService() {
        return randomNumberService;
    }
}
