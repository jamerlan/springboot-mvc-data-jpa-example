package com.example.myproject.service;

import com.example.myproject.domain.*;
import com.example.myproject.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private RandomNumberService randomNumberService;

    private QuizServiceImpl quizService;

    @Before
    public void setUp() throws Exception {
        quizService = new QuizServiceImpl();
        quizService.setAnswerRepository(answerRepository);
        quizService.setQuestionRepository(questionRepository);
        quizService.setQuizRepository(quizRepository);
        quizService.setRandomNumberService(randomNumberService);
    }

    @Test
    public void testGenerateQuiz() throws Exception {
        when(questionRepository.count()).thenReturn(10L);
        when(randomNumberService.getNextLong(anyLong())).thenReturn(10L);

        when(questionRepository.findOne(anyLong())).thenReturn(new Question("1", new Answer()))
                .thenReturn(new Question("2", new Answer())).thenReturn(new Question("3", new Answer()))
                .thenReturn(new Question("4", new Answer())).thenReturn(new Question("5", new Answer()));

        Quiz quiz = quizService.generateQuiz();

        verify(randomNumberService, times(Constants.QUIZ_QUESTIONS_COUNT)).getNextLong(9L);
        verify(questionRepository, times(Constants.QUIZ_QUESTIONS_COUNT)).findOne(11L);
        verify(questionRepository, times(1)).count();
        assertEquals(quiz.getQuestionsToAnswer().size(), Constants.QUIZ_QUESTIONS_COUNT);
    }

    @Test
    public void testGetQuiz() throws Exception {
        Quiz q = new Quiz();
        q.setId(5);
        q.setQuestionsToAnswer(new HashSet<>());
        q.setScore(0);

        when(quizRepository.findOne(anyLong())).thenReturn(q);
        Quiz quiz = quizService.getQuiz(1L);
        assertEquals(q, quiz);
        verify(quizRepository, times(1)).findOne(anyLong());
    }

    @Test
    public void testGetQuizQuestion() throws Exception {
        //TODO: skipped intentionally
    }

    @Test
    public void testGetRandomAnswers() throws Exception {
        //TODO: skipped intentionally
    }

    @Test
    public void testSaveAnswer() throws Exception {
        //TODO: skipped intentionally
    }
}