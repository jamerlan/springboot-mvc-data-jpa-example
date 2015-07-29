package com.example.myproject.web;

import com.example.myproject.domain.Answer;
import com.example.myproject.domain.Question;
import com.example.myproject.domain.Quiz;
import com.example.myproject.service.QuizService;
import com.example.myproject.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Controller
public class QuizController {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Inject
    private QuizService quizService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCreateUserView() {
        logger.debug("Called getCreateUserView()");

        Quiz quiz = quizService.generateQuiz();

        QuizForm quizForm = getQuizForm(quiz);
        logger.debug("Result -> quizForm: " + quizForm);
        return new ModelAndView("quiz", "quizForm", quizForm);
    }

    private QuizForm getQuizForm(Quiz quiz) {
        int currentQuestion = Constants.QUIZ_QUESTIONS_COUNT - quiz.getQuestionsToAnswer().size();
        return new QuizForm(quiz.getId(), currentQuestion, Constants.QUIZ_QUESTIONS_COUNT);
    }

    @RequestMapping(value = "/getQuestion")
    @ResponseBody
    public QuestionForm getQuestion(@RequestParam long quizId) {
        logger.debug("Called getQuestion(quizId) with quizId: " + quizId);
        Question question = quizService.getQuizQuestion(quizId);
        Quiz quiz = quizService.getQuiz(quizId);

        Set<Answer> answers = new HashSet<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(quizService.getRandomAnswers());

        QuizForm quizForm = getQuizForm(quiz);
        logger.debug("Result -> quizForm: " + quizForm);
        return new QuestionForm(quizForm, question, answers);
    }

    @RequestMapping(value = "/saveAnswer")
    @ResponseBody
    public boolean saveAnswer(@RequestParam long quizId, @RequestParam long questionId, @RequestParam long answerId) {
        logger.debug("Called saveAnswer(quizId, questionId, answerId) with quizId: " + quizId + " questionId: " +
            questionId + " answerId: " + answerId);
        quizService.saveAnswer(quizId, questionId, answerId);
        return true;
    }

    @RequestMapping(value = "/getResult")
    @ResponseBody
    public int getQuizScore(@RequestParam long quizId) {
        return quizService.getQuiz(quizId).getScore();
    }

}
