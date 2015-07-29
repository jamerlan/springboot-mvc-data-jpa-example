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
