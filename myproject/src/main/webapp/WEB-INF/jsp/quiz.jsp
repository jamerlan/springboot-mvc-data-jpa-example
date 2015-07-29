<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<h1 id="heading"><spring:message code="quiz.title"/></h1>

<script>
<%--@elvariable id="quizForm" type="com.example.myproject.web.QuizForm"--%>
    var quiz = {
        quizId: <c:out value="${quizForm.getQuizId()}"/>,
        current: <c:out value="${quizForm.getCurrent()}"/>,
        total: <c:out value="${quizForm.getTotal()}"/>,
        timer: 30,
        currentQuestionId: -1,
        timerId: -1
    };

    function updateProgress() {
        $("#current").text(quiz.current);
        $("#total").text(quiz.total);
    }

    function enableSubmit() {
        $("#submit").prop('disabled', false);
    }

    function disableSubmit() {
        $("#submit").prop('disabled', true);
    }

    function startTimer() {
        if(quiz.timer > 0) {
            quiz.timerId = window.setTimeout(startTimer, 1000);
            quiz.timer = quiz.timer - 1;
            $("#timer").text(quiz.timer);
        } else {
            submitQuestion();
        }
    }

    function updateQuestion(quiz, data) {
        quiz.current = data.quizForm.current;
        quiz.total = data.quizForm.total;
        quiz.currentQuestionId = data.question.id;

        if(quiz.timer > 0) {
            window.clearTimeout(quiz.timerId);
        }

        quiz.timer = 30;

        updateProgress();

        disableSubmit();

        startTimer();

        $("#question").text(data.question.questionText);

        $('#answers').empty();

        for (var i = 0; i < data.answers.length; i++) {
            var radio = $('<input type="radio" name="dynradio" onclick="enableSubmit()" />');
            radio.attr("answerId", data.answers[i].id);
            $('#answers').append(radio).append(data.answers[i].answerText).append("<br/>");
        }
    }

    function getNextQuestion(quiz) {
        $.getJSON("/getQuestion.json", {quizId: quiz.quizId},
            function (data) {
                updateQuestion(quiz, data);
            }
        );
    }

    function showResult(score) {
        $("#questions").hide();
        $("#heading").hide();
        $("#results").show();
        $("#result").text(score);
        $("#from").text(quiz.total);
    }

    function getResult() {
        $.getJSON("/getResult.json", {quizId: quiz.quizId},
            function (data) {
                showResult(data);
            }
        );
    }

    function next() {
        if(quiz.current < quiz.total) {
            getNextQuestion(quiz);
        } else {
            getResult();
        }
    }

    function submitAnswer(ansId) {
        $.getJSON("/saveAnswer.json", {quizId: quiz.quizId, questionId: quiz.currentQuestionId, answerId: ansId},
            function (data) {
                next();
            }
        );
    }
    function submitQuestion() {
        var selectedRadio = $("#answers input[type='radio']:checked");
        var answerId = -1;
        if (selectedRadio.length > 0) {
            answerId = selectedRadio.attr("answerId");
        }
        submitAnswer(answerId);
    }

    $(function(){
        updateProgress();
        $("#results").hide();
        getNextQuestion(quiz);
    });
</script>


<br/>
<div id="questions">
    <h2><spring:message code="quiz.progress"/> <span id="current"></span>/<span id="total"></span></h2>
    <h2><spring:message code="quiz.timer"/>: <span style="color:red" id="timer"></span></h2>
    <br/>
    <h2><spring:message code="quiz.question"/>: <span id="question"></span></h2>
    <br/>
    <h2><spring:message code="quiz.answers"/>:</h2>
    <br/>
    <span id="answers"></span>

    <br/>
    <br/>
    <input id="submit" type="button" onclick="submitQuestion()" value="<spring:message code="quiz.submit"/>"/>
</div>

<br/><br/>
<div id="results">
    <h1><spring:message code="quiz.result"/>: <span id="result"></span> <spring:message code="quiz.from"/> <span id="from"></span></h1>
</div>


</body>
</html>
