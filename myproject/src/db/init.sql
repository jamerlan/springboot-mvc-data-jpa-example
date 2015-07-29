DROP DATABASE IF EXISTS quiz;
CREATE DATABASE quiz default charset utf8;

GRANT ALL ON quiz.* TO quizuser@localhost IDENTIFIED BY 'quizpassword';
