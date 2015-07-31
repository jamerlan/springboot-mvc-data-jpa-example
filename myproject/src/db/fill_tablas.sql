DROP PROCEDURE IF EXISTS insert_answers;
DELIMITER // ;
CREATE PROCEDURE insert_answers ()
  BEGIN
    DECLARE varcount INT;
    DECLARE varmax INT;

    SET varcount = 1;
    SET varmax = 300000;

    WHILE varcount <= varmax DO
      INSERT INTO answers(id, answer_text)
      VALUES(varcount, CONVERT(varcount, char));
      SET varcount = varcount + 1;
    END WHILE;
  END ;
//
DELIMITER ; //

DROP PROCEDURE IF EXISTS insert_questions;
DELIMITER // ;
CREATE PROCEDURE insert_questions ()
  BEGIN
    DECLARE varcount INT;
    DECLARE varmax INT;

    SET varcount = 1;
    SET varmax = 300000;

    WHILE varcount <= varmax DO
      INSERT INTO questions(id, question_text, correct_answer_id)
      VALUES(varcount, CONCAT('Сколько будет ', varcount, ' + 0 = ?'), varcount);
      SET varcount = varcount + 1;
    END WHILE;
  END ;
//
DELIMITER ; //

delete from quiz_questions;
delete from quiz;
delete FROM questions;
delete FROM answers;

CALL insert_answers();
CALL insert_questions();
