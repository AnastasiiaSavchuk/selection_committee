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
       ('kulbabyn@gmail.com', 'qetuop', 2),
       ('gevorga@gmail.com', 'pouteq', 2),
       ('korostylova@gmail.com', 'wryipo', 2),
       ('smeshanov@gmail.com', 'poiyrw', 2),
       ('kolyabin@gmail.com', 'poiuyt', 2),
       ('mamonov@gmail.com', 'tyuiop', 2),
       ('salamin@gmail.com', 'qawsed', 2),
       ('kuznetsova@gmail.com', 'rftgyh', 2),
       ('rosinkova@gmail.com', 'yjukil', 2);

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
VALUES (1, 'Anastasiia', 'Andriivna', 'Savchuk', 'Kropyvnytskyi', 'Kirovohrad region', 'School №25', '', 0),
       (2, 'Petro', 'Anatoliyovych', 'Rasyansky', 'Lviv', 'Lviv region', 'School №25', '', 0),
       (3, 'Roman', 'Dmitrovich', 'Kulbabyn', 'Odessa', 'Odessa region', 'Gymnasium №25', '', 0),
       (4, 'Sandra', 'Semenivna', 'Gevorga', 'Poltava', 'Poltava region', 'Lyceum №15', '', 0),
       (5, 'Olga', 'Andriivna', 'Korostylova', 'Vinnytsia', 'Vinnytsia region', 'Gymnasium №18', '', 0),
       (6, 'Konstantyn', 'Abramovich', 'Smeshanov', 'Kiev', 'Kiev region', 'School №147', '', 0),
       (7, 'Igor', 'Evstahiyovych', 'Kolyabin', 'Mykolaiv', 'Mykolaiv region', 'Lyceum №6', '', 0),
       (8, 'Mykhailo', 'Olegovich', 'Mamonov', 'Zakarpattia', 'Zakarpattia region', 'Gymnasium №95', '', 0),
       (9, 'Stepan', 'Volodymyrovych', 'Salamin', 'Khmelnytskyi', 'Khmelnytskyi region', 'School №34', '', 0),
       (10, 'Catherine', 'Abdulovna', 'Kuznetsova', 'Sumy', 'Sumy region', 'Lyceum №65', '', 0),
       (11, 'Inna', 'Mykolayivna', 'Rosinkova', 'Dnipropetrovsk', 'Dnipropetrovsk region', 'School №92', '', 0);

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


CREATE TABLE IF NOT EXISTS faculty
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    budget_qty INT NOT NULL,
    total_qty  INT NOT NULL
);

INSERT INTO faculty(budget_qty, total_qty)
VALUES (7, 15),
       (8, 15),
       (5, 10),
       (3, 8);

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
    id     INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL
);

INSERT INTO application_status(status)
VALUES ('NOT_PROCESSED'),
       ('BLOCKED'),
       ('NOT_APPROVED'),
       ('BUDGET_APPROVED'),
       ('CONTRACT_APPROVED');

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
VALUES (2, 1, 0, 0, 1),#1 'Faculty of Electronics and computer technologies' 1 Maths 2 Physics 3 English 4 Ukrainian language
       (2, 2, 0, 0, 1),#2 'Faculty of Foreign Languages' 3 English 4 Ukrainian language
       (2, 4, 0, 0, 2),#4 'Faculty of Financial Management and Business' 3 English 4 Ukrainian language 5 History
       (3, 2, 0, 0, 2),#2 'Faculty of Foreign Languages' 3 English 4 Ukrainian language
       (3, 3, 0, 0, 1),#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language
       (4, 1, 0, 0, 1),#1 'Faculty of Electronics and computer technologies' 1 Maths 2 Physics 3 English 4 Ukrainian language
       (4, 3, 0, 0, 2),#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language
       (5, 1, 0, 0, 2),#1 'Faculty of Electronics and computer technologies' 1 Maths 2 Physics 3 English 4 Ukrainian language
       (5, 3, 0, 0, 1),#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language
       (5, 4, 0, 0, 1),#4 'Faculty of Financial Management and Business' 3 English 4 Ukrainian language 5 History
       (6, 2, 0, 0, 1),#2 'Faculty of Foreign Languages' 3 English 4 Ukrainian language
       (6, 3, 0, 0, 1),#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language
       (7, 1, 0, 0, 2),#1 'Faculty of Electronics and computer technologies' 1 Maths 2 Physics 3 English 4 Ukrainian language
       (7, 4, 0, 0, 2),#4 'Faculty of Financial Management and Business' 3 English 4 Ukrainian language 5 History
       (8, 4, 0, 0, 2),#4 'Faculty of Financial Management and Business' 3 English 4 Ukrainian language 5 History
       (9, 2, 0, 0, 2),#2 'Faculty of Foreign Languages' 3 English 4 Ukrainian language
       (9, 3, 0, 0, 1),#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language
       (10, 1, 0, 0, 2),#1 'Faculty of Electronics and computer technologies' 1 Maths 2 Physics 3 English 4 Ukrainian language
       (10, 3, 0, 0, 1);#3 'Faculty of Applied Mathematics and Informatics' 1 Maths 3 English 4 Ukrainian language

CREATE TABLE IF NOT EXISTS grade
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    subject_id INT NOT NULL,
    grade      INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO grade(subject_id, grade)
VALUES (1, 195),
       (2, 186),
       (3, 174),
       (4, 155),
       (3, 139),
       (4, 147),
       (1, 169),
       (4, 139),
       (5, 154),
       (3, 165),
       (4, 148),
       (1, 181),
       (3, 149),
       (4, 134),
       (1, 157),
       (2, 149),
       (3, 163),
       (4, 186),
       (1, 158),
       (3, 178),
       (4, 169),
       (1, 174),
       (2, 169),
       (3, 148),
       (4, 145),
       (1, 196),
       (3, 145),
       (4, 179),
       (4, 169),
       (3, 158),
       (5, 147),
       (3, 154),
       (4, 169),
       (4, 167),
       (3, 136),
       (4, 147),
       (1, 159),
       (2, 163),
       (3, 184),
       (4, 196),
       (3, 198),
       (4, 184),
       (5, 179),
       (3, 160),
       (4, 171),
       (5, 185),
       (3, 140),
       (4, 159),
       (1, 136),
       (3, 187),
       (4, 163),
       (1, 169),
       (2, 163),
       (3, 149),
       (4, 170),
       (1, 196),
       (3, 145),
       (4, 180);

CREATE TABLE IF NOT EXISTS application_grade
(
    application_id INT NOT NULL,
    grade_id       INT NOT NULL UNIQUE,
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
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (4, 10),
       (4, 11),
       (5, 12),
       (5, 13),
       (5, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (6, 18),
       (7, 19),
       (7, 20),
       (7, 21),
       (8, 22),
       (8, 23),
       (8, 24),
       (8, 25),
       (9, 26),
       (9, 27),
       (9, 28),
       (10, 29),
       (10, 30),
       (10, 31),
       (11, 32),
       (11, 33),
       (12, 34),
       (12, 35),
       (12, 36),
       (13, 37),
       (13, 38),
       (13, 39),
       (13, 40),
       (14, 41),
       (14, 42),
       (14, 43),
       (15, 44),
       (15, 45),
       (15, 46),
       (16, 47),
       (16, 48),
       (17, 49),
       (17, 50),
       (17, 51),
       (18, 52),
       (18, 53),
       (18, 54),
       (18, 55),
       (19, 56),
       (19, 57),
       (19, 58);
