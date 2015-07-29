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
</script>

<script src="/js/scripts.js"></script>

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
