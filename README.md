# springboot-mvc-data-jpa-example

Для запуска проекта требуется база MySQL, java 8 и maven 3+

1) Сначала надо выполнить скрипт:  https://github.com/jamerlan/springboot-mvc-data-jpa-example/blob/master/myproject/src/db/init.sql

2) git clone git@github.com:jamerlan/springboot-mvc-data-jpa-example.git

3) cd springboot-mvc-data-jpa-example/myproject

4) mvn spring-boot:run

5) Открыть в браузере http://localhost:8080/


Чтобы заполнить таблицы answers и questions по 300 000 записей, нужно выполнить скрипт
mysql -u root quiz < src/db/fill_tablas.sql
