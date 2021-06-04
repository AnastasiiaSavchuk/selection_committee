drop database if exists selection_committee;
CREATE DATABASE IF NOT EXISTS selection_committee;
USE selection_committee;

CREATE TABLE IF NOT EXISTS language
(
    id        INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    language  VARCHAR(45) NOT NULL,
    lang_code VARCHAR(20) NOT NULL
);

INSERT INTO language(language, lang_code)
VALUES ('English', 'en'),
       ('Українська', 'uk');

CREATE TABLE IF NOT EXISTS role
(
    id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(45) UNIQUE
);

INSERT INTO role(role)
VALUES ('ADMIN'),
       ('USER');

CREATE TABLE IF NOT EXISTS user
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    role_id  INT          NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO user(email, password, role_id)
VALUES ('savchuknesty@gmail.com', 'qwerty', 1),
       ('rasyansky@gmail.com', 'ytrewq', 2),
       ('kulbabyn@gmail.com', 'asdfgh', 2),
       ('gevorga@gmail.com', 'hgfdsa', 2),
       ('korostylova@gmail.com', 'zxcvbn', 2),
       ('smeshanov@gmail.com', 'nbvcxz', 2),
       ('kolyabin@gmail.com', 'poiuyt', 2),
       ('mamonov@gmail.com', 'tyuiop', 2),
       ('salamin@gmail.com', 'lkjhgf', 2),
       ('kuznetsova@gmail.com', 'fghjkl', 2),
       ('rosinkova@gmail.com', 'mnbvcx', 2);

CREATE TABLE IF NOT EXISTS applicant
(
    user_id     INT         NOT NULL UNIQUE,
    first_name  VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    city        VARCHAR(50) NOT NULL,
    region      VARCHAR(50) NOT NULL,
    school_name VARCHAR(50) NOT NULL,
    certificate MEDIUMBLOB  NOT NULL,
    is_blocked  INT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO applicant(user_id, first_name, middle_name, last_name, city, region, school_name, certificate,
                      is_blocked)

VALUES (1, 'Анастасія', 'Андріївна', 'Савчук', 'Львів', 'Львівська область', 'Школа №25', '', 0),
       (2, 'Перро', 'Анатолійович', 'Расянський', 'Львів', 'Львівська область', 'Школа №25', '', 0),
       (3, 'Роман', 'Дмитрович', 'Кульбабин', 'Одеса', 'Одеська область', 'Гімназія №25', '', 0),
       (4, 'Олександра', 'Семенівна', 'Геворга', 'Полтава', 'Полтавська область', 'Ліцей №15', '', 0),
       (5, 'Ольга', 'Миронівна', 'Коростильова', 'Вінниця', 'Вінницька область', 'Гімназія №18', '', 0),
       (6, 'Дмитро', 'Степанович', 'Смішанов', 'Київ', 'Київська область', 'Школа №147', '', 0),
       (7, 'Ігор', 'Олександрович', 'Колябін', 'Миколаїв', 'Миколаївська область', 'Ліцей №6', '', 0),
       (8, 'Михайло', 'Олегович', 'Мамонов', 'Міжгірне', 'Закарпатська область', 'Гімназія №95', '', 0),
       (9, 'Степан', 'Володимирович', 'Салямін', 'Хмельницький', 'Хмельницька область', 'Школа №34', '', 0),
       (10, 'Катерина', 'Сергіївна', 'Зелена', 'Суми', 'Сумська область', 'Ліцей №65', '', 0),
       (11, 'Інна', 'Миколаївна', 'Сонячна', 'Дніпропетровськ', 'Дніпропетровська область', 'Школа №92', '', 0);

CREATE TABLE IF NOT EXISTS subject
(
    id            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    passing_grade INT NOT NULL
);

INSERT INTO subject(passing_grade)
VALUES (140),
       (135),
       (145),
       (140),
       (135);

CREATE TABLE IF NOT EXISTS subject_translation
(
    subject_id  INT           NOT NULL,
    language_id INT           NOT NULL,
    subject     NVARCHAR(120) NOT NULL UNIQUE,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (language_id) REFERENCES language (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO subject_translation(subject_id, language_id, subject)
VALUES (1, 1, 'Maths'),
       (1, 2, 'Математика'),
       (2, 2, 'Фізика'),
       (2, 1, 'Physics'),
       (3, 1, 'English'),
       (3, 2, 'Англійська мова'),
       (4, 1, 'Ukrainian language'),
       (4, 2, 'Українська мова'),
       (5, 1, 'History'),
       (5, 2, 'Історія');

CREATE TABLE IF NOT EXISTS grade
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id    INT NOT NULL,
    subject_id INT NOT NULL,
    grade      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO grade(user_id, subject_id, grade)
VALUES (2, 1, 147),
       (2, 2, 169),
       (2, 3, 139),
       (2, 4, 154),
       (2, 5, 165),

       (3, 1, 148),
       (3, 3, 149),
       (3, 4, 134),

       (4, 1, 149),
       (4, 2, 163),
       (4, 3, 186),
       (4, 4, 158),

       (5, 1, 169),
       (5, 2, 174),
       (5, 3, 169),
       (5, 4, 148),
       (5, 5, 155),

       (6, 1, 196),
       (6, 3, 179),
       (6, 4, 169),

       (7, 1, 147),
       (7, 2, 154),
       (7, 3, 169),
       (7, 4, 167),
       (7, 5, 136),

       (8, 3, 163),
       (8, 4, 184),
       (8, 5, 196),

       (9, 1, 198),
       (9, 3, 179),
       (9, 4, 160),

       (10, 1, 185),
       (10, 2, 140),
       (10, 3, 159),
       (10, 4, 136),

       (11, 2, 163),
       (11, 4, 149),
       (11, 5, 170);

CREATE TABLE IF NOT EXISTS faculty
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    total_qty  INT NOT NULL,
    budget_qty INT NOT NULL
);

INSERT INTO faculty(total_qty, budget_qty)
VALUES (10, 5),
       (11, 6),
       (9, 4),
       (8, 3);

CREATE TABLE IF NOT EXISTS faculty_translation
(
    faculty_id  INT           NOT NULL,
    language_id INT           NOT NULL,
    faculty     NVARCHAR(120) NOT NULL UNIQUE,
    FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (language_id) REFERENCES language (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO faculty_translation(faculty_id, language_id, faculty)
VALUES (1, 1, 'Faculty of Electronics and Computer Technologies'),
       (1, 2, 'Факультет електроніки та комп’ютерних технологій'),
       (2, 1, 'Faculty of Foreign Languages'),
       (2, 2, 'Факультет іноземних мов'),
       (3, 1, 'Faculty of Applied Mathematics and Informatics'),
       (3, 2, 'Факультет прикладної математики та інформатики'),
       (4, 1, 'Faculty of Financial Management and Business'),
       (4, 2, 'Факультет управління фінансами та бізнесу');

CREATE TABLE IF NOT EXISTS faculty_subject
(
    faculty_id INT NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO faculty_subject (faculty_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 3),
       (3, 4),
       (4, 3),
       (4, 4),
       (4, 5);

CREATE TABLE IF NOT EXISTS application_status
(
    id     INT         NOT NULL PRIMARY KEY,
    status VARCHAR(20) NOT NULL
);

INSERT INTO application_status(id,status)
VALUES (0,'IN_PROCESSING'),
       (1,'REJECTED'),
       (2,'BUDGET_APPROVED'),
       (3,'CONTRACT_APPROVED');

CREATE TABLE IF NOT EXISTS application
(
    id                   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id              INT NOT NULL,
    faculty_id           INT NOT NULL,
    sum_of_grades        INT,
    average_grade        INT,
    applicationStatus_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (applicationStatus_id) REFERENCES application_status (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO application (user_id, faculty_id, sum_of_grades, average_grade, applicationStatus_id)
VALUES (2, 1, 0, 0, 0),#2 1 'Faculty of Electronics and computer technologies' 1 Maths (1) 2 Physics (2) 3 English (3) 4 Ukrainian language (4)
       (2, 2, 0, 0, 0),#2 2 'Faculty of Foreign Languages' 3 English (3) 4 Ukrainian language (4)
       (2, 4, 0, 0, 0),#2 4 'Faculty of Financial Management and Business' 3 English (3) 4 Ukrainian language (4) 5 History(5)
       (3, 2, 0, 0, 0),#3 2 'Faculty of Foreign Languages' 3 English(7) 4 Ukrainian language(8)
       (3, 3, 0, 0, 0),#3 3 'Faculty of Applied Mathematics and Informatics' 1 Maths (6) 3 English (7) 4 Ukrainian language(8)
       (4, 1, 0, 0, 0),#4 1 'Faculty of Electronics and computer technologies' 1 Maths(9) 2 Physics(10) 3 English(11) 4 Ukrainian language(12)
       (4, 3, 0, 0, 0),#4 3 'Faculty of Applied Mathematics and Informatics' 1 Maths(9) 3 English(11) 4 Ukrainian language(12)
       (5, 1, 0, 0, 0),#5 1 'Faculty of Electronics and computer technologies' 1 Maths(13) 2 Physics(14) 3 English(15) 4 Ukrainian language(16)
       (5, 3, 0, 0, 0),#5 3 'Faculty of Applied Mathematics and Informatics' 1 Maths(13) 3 English(15) 4 Ukrainian language(16)
       (5, 4, 0, 0, 0),#5 4 'Faculty of Financial Management and Business' 3 English(15) 4 Ukrainian language(16) 5 History(17)
       (6, 2, 0, 0, 0),#6 2 'Faculty of Foreign Languages' 3 English(19) 4 Ukrainian language(20)
       (6, 3, 0, 0, 0),#6 3 'Faculty of Applied Mathematics and Informatics' 1 Maths(18) 3 English(19) 4 Ukrainian language(20)
       (7, 1, 0, 0, 0),#7 1 'Faculty of Electronics and computer technologies' 1 Maths(21) 2 Physics(22) 3 English(23) 4 Ukrainian language(24)
       (7, 4, 0, 0, 0),#7 4 'Faculty of Financial Management and Business' 3 English(23) 4 Ukrainian language(24) 5 History(25)
       (8, 4, 0, 0, 0),#8 4 'Faculty of Financial Management and Business' 3 English(26) 4 Ukrainian language(27) 5 History(28)
       (9, 2, 0, 0, 0),#9 2 'Faculty of Foreign Languages' 3 English(30) 4 Ukrainian language(31)
       (9, 3, 0, 0, 0),#9 3 'Faculty of Applied Mathematics and Informatics' 1 Maths(29) 3 English(30) 4 Ukrainian language(31)
       (10, 1, 0, 0, 0),#10 1'Faculty of Electronics and computer technologies' 1 Maths(32) 2 Physics(33) 3 English(34) 4 Ukrainian language(35)
       (10, 3, 0, 0, 0),#10 3 'Faculty of Applied Mathematics and Informatics' 1 Maths(32) 3 English(34) 4 Ukrainian language(35)
       (11, 2, 0, 0, 0),#11 2 'Faculty of Foreign Languages' 3 English(36) 4 Ukrainian language(37)
       (11, 4, 0, 0, 0);#11 4 'Faculty of Financial Management and Business' 3 English(36) 4 Ukrainian language(37) 5 History(38)

CREATE TABLE IF NOT EXISTS application_grade
(
    application_id INT NOT NULL,
    grade_id       INT NOT NULL,
    FOREIGN KEY (application_id) REFERENCES application (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (grade_id) REFERENCES grade (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TRIGGER application_grade_AFTER_INSERT_SUM
    AFTER INSERT
    ON application_grade
    FOR EACH ROW
    UPDATE application
    SET sum_of_grades = (SELECT SUM(g.grade)
                         FROM grade g
                                  INNER JOIN application_grade ag ON ag.grade_id = g.id
                                  INNER JOIN subject s ON s.id = g.subject_id
                         WHERE ag.application_id = application.id);

CREATE TRIGGER application_grade_AFTER_INSERT_AVR
    AFTER INSERT
    ON application_grade
    FOR EACH ROW
    UPDATE application
    SET average_grade = (SELECT AVG(g.grade)
                         FROM grade g
                                  INNER JOIN application_grade ag ON ag.grade_id = g.id
                                  INNER JOIN subject s ON s.id = g.subject_id
                         WHERE ag.application_id = application.id);

INSERT INTO application_grade(application_id, grade_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 3),
       (2, 4),
       (3, 3),
       (3, 4),
       (3, 5),
       (4, 7),
       (4, 8),
       (5, 6),
       (5, 7),
       (5, 8),
       (6, 9),
       (6, 10),
       (6, 11),
       (6, 12),
       (7, 9),
       (7, 11),
       (7, 12),
       (8, 13),
       (8, 14),
       (8, 15),
       (8, 16),
       (9, 13),
       (9, 15),
       (9, 16),
       (10, 15),
       (10, 16),
       (10, 17),
       (11, 19),
       (11, 20),
       (12, 18),
       (12, 19),
       (12, 20),
       (13, 21),
       (13, 22),
       (13, 23),
       (13, 24),
       (14, 23),
       (14, 24),
       (14, 25),
       (15, 26),
       (15, 27),
       (15, 28),
       (16, 30),
       (16, 31),
       (17, 29),
       (17, 30),
       (17, 31),
       (18, 32),
       (18, 33),
       (18, 34),
       (18, 35),
       (19, 32),
       (19, 34),
       (19, 35),
       (20, 36),
       (20, 37),
       (21, 36),
       (21, 37),
       (21, 38);