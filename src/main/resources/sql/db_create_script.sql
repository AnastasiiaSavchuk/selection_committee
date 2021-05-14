drop database if exists selection_committee;
CREATE DATABASE IF NOT EXISTS selection_committee;
USE selection_committee;

CREATE TABLE IF NOT EXISTS languages
(
    id        INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    language  VARCHAR(45) NOT NULL,
    lang_code VARCHAR(20) NOT NULL
);

INSERT INTO languages(language, lang_code)
VALUES ('English', 'en'),
       ('Українська', 'uk');

CREATE TABLE IF NOT EXISTS roles
(
    id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(45)
);

INSERT INTO roles(role)
VALUES ('ADMIN'),
       ('USER');

CREATE TABLE IF NOT EXISTS users
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    role_id  INT          NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO users(email, password, role_id)
VALUES ('admin@gmail.com', 'qwerty', 1),
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

CREATE TABLE IF NOT EXISTS applicants
(
    user_id     INT         NOT NULL,
    language_id INT         NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    city        VARCHAR(50) NOT NULL,
    region      VARCHAR(50) NOT NULL,
    school_name VARCHAR(50) NOT NULL,
    is_blocked  INT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO applicants(user_id, language_id, first_name, middle_name, last_name, city, region, school_name, is_blocked)
VALUES (1, 1, 'Anastasiia', 'Andriivna', 'Osadchuk', 'Kropyvnytskyi', 'Kirovohrad region', 'School №25', 0),
       (1, 2, 'Анастасія', 'Андріївна', 'Осадчук', 'Кропивницький', 'Кіровоградська область', 'Школа №25', 0),
       (2, 1, 'Petro', 'Anatoliyovych', 'Rasyansky', 'Lviv', 'Lviv region', 'School №25', 0),
       (2, 2, 'Петро', 'Анатолійович', 'Расянський', 'Львів', 'Львівська область', 'Школа №25', 0),
       (3, 1, 'Roman', 'Dmitrovich', 'Kulbabyn', 'Odessa', 'Odessa region', 'Gymnasium №25', 0),
       (3, 2, 'Роман', 'Дмитрович', 'Кульбабин', 'Одеса', 'Одеська область', 'Гімназія №25', 0),
       (4, 1, 'Sandra', 'Semenivna', 'Gevorga', 'Poltava', 'Poltava region', 'Lyceum №15', 0),
       (4, 2, 'Сандра', 'Семенівна', 'Геворга', 'Полтава', 'Полтавська область', 'Ліцей №15', 0),
       (5, 1, 'Olga', 'Andriivna', 'Korostylova', 'Vinnytsia', 'Vinnytsia region', 'Gymnasium №18', 0),
       (5, 2, 'Ольга', 'Андріївна', 'Коростильова', 'Вінниця', 'Вінницька область', 'Гімназія №18', 0),
       (6, 1, 'Konstantyn', 'Abramovich', 'Smeshanov', 'Kiev', 'Kiev region', 'School №147', 0),
       (6, 2, 'Константин', 'Абрамович', 'Смішанов', 'Київ', 'Київська область', 'Школа №147', 0),
       (7, 1, 'Igor', 'Evstahiyovych', 'Kolyabin', 'Mykolaiv', 'Mykolaiv region', 'Lyceum №6', 0),
       (7, 2, 'Ігор', 'Євстахійович', 'Колябін', 'Mykolaiv', 'Миколаївська область', 'Ліцей №6', 0),
       (8, 1, 'Mykhailo', 'Olegovich', 'Mamonov', 'Zakarpattia', 'Zakarpattia region', 'Gymnasium №95', 0),
       (8, 2, 'Михайло', 'Олегович', 'Мамонов', 'Закарпаття', 'Закарпатська область', 'Гімназія №95', 0),
       (9, 1, 'Stepan', 'Volodymyrovych', 'Salamin', 'Khmelnytskyi', 'Khmelnytskyi region', 'School №34', 0),
       (9, 2, 'Степан', 'Володимирович', 'Салямін', 'Хмельницький', 'Хмельницька область', 'Школа №34', 0),
       (10, 1, 'Catherine', 'Abdulovna', 'Kuznetsova', 'Sumy', 'Sumy region', 'Lyceum №65', 0),
       (10, 2, 'Катерина', 'Абдуловна', 'Кузнецова', 'Суми', 'Сумська область', 'Ліцей №65', 0),
       (11, 1, 'Inna', 'Mykolayivna', 'Rosinkova', 'Dnipropetrovsk', 'Dnipropetrovsk region', 'School №92', 0),
       (11, 2, 'Інна', 'Миколаївна', 'Росинкова', 'Дніпропетровськ', 'Дніпропетровська область', 'Школа №92', 0);

CREATE TABLE IF NOT EXISTS school_certificates
(
    id                 INT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    school_certificate MEDIUMBLOB NOT NULL,
    FOREIGN KEY (id) REFERENCES applicants (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO school_certificates(school_certificate)
VALUES (''),
       (''),
       (''),
       (''),
       (''),
       (''),
       (''),
       (''),
       (''),
       (''),
       ('');

CREATE TABLE IF NOT EXISTS faculties_i18n
(
    id          INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    language_id INT          NOT NULL,
    name        VARCHAR(120) NOT NULL,
    budget_qty  INT          NOT NULL,
    total_qty   INT          NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO faculties_i18n(language_id, name, budget_qty, total_qty)
VALUES (1, 'Faculty of Electronics and computer technologies', 7, 15),
       (2, 'Факультет електроніки та комп’ютерних технологій', 7, 15),
       (1, 'Faculty of Foreign Languages', 8, 15),
       (2, 'Факультет іноземних мов', 8, 15),
       (1, 'Faculty of Applied Mathematics and Informatics', 5, 10),
       (2, 'Факультет прикладної математики та інформатики', 5, 10),
       (1, 'Faculty of Financial Management and Business', 3, 8),
       (2, 'Факультет управління фінансами та бізнесу', 3, 8);

CREATE TABLE IF NOT EXISTS subjects_i18n
(
    id          INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    language_id INT          NOT NULL,
    subject     VARCHAR(120) NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO subjects_i18n(language_id, subject)
VALUES (1, 'Maths'), (2, 'Математика'),
       (1, 'Physics'), (2, 'Фізика'),
       (1, 'English'), (2, 'Англійська мова'),
       (1, 'Ukrainian language'), (2, 'Українська мова'),
       (1, 'History'), (2, 'Історія');

CREATE TABLE IF NOT EXISTS faculties_subjects
(
    faculty_id INT NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculties_i18n (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects_i18n (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO faculties_subjects (faculty_id, subject_id)
VALUES (1, 1), (1, 3), (1, 5), (1, 7),
       (2, 2), (2, 4), (2, 6), (2, 8),
       (3, 5), (3, 7),
       (4, 6), (4, 8),
       (5, 1), (5, 5), (5, 7),
       (6, 2), (6, 6), (6, 8),
       (7, 5), (7, 7), (7, 9),
       (8, 6), (8, 8), (8, 10);

CREATE TABLE IF NOT EXISTS applicationStatuses_i18n
(
    id          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    language_id INT         NOT NULL,
    status      VARCHAR(20) NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO applicationStatuses_i18n(language_id, status)
VALUES (1, 'NOT_PROCESSED'), (2, 'НЕ_ ОБРОБЛЕНО'),
       (1, 'NOT_APPROVED'), (2, 'НЕ_СХВАЛЕНО'),
       (1, 'BUDGET_APPROVED'), (2, 'БЮДЖЕТ_ЗАТВЕРДЖЕНО'),
       (1, 'CONTRACT_APPROVED'), (2, 'КОНТРАКТ_ЗАТВЕРДЖЕНО');

CREATE TABLE IF NOT EXISTS applications
(
    id                   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    applicant_id         INT NOT NULL,
    faculty_id           INT NOT NULL,
    applicationStatus_id INT NOT NULL,
    FOREIGN KEY (applicant_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (faculty_id) REFERENCES faculties_i18n (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (applicationStatus_id) REFERENCES applicationStatuses_i18n (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO applications (applicant_id, faculty_id, applicationStatus_id)
VALUES (3, 1, 1),(4, 2, 2),
       (3, 5, 1),(4, 6, 2),
       (5, 3, 1), (6, 4, 2),
       (5, 7, 1), (6, 8, 2),
       (7, 1, 1), (8, 2, 2),
       (7, 3, 1), (8, 4, 2),
       (7, 5, 1), (8, 6, 2),
       (7, 7, 1), (8, 8, 2),
       (9, 7, 1), (10, 8, 2),
       (11, 1, 1), (12, 2, 2),
       (11, 3, 1), (12, 4, 2),
       (13, 3, 1), (14, 4, 2),
       (13, 5, 1), (14, 6, 2),
       (13, 7, 1), (14, 8, 2),
       (15, 3, 1), (16, 4, 2),
       (17, 1, 1), (18, 2, 2),
       (17, 5, 1), (18, 6, 2),
       (19, 1, 1), (20, 2, 2),
       (19, 3, 1), (20, 4, 2),
       (19, 5, 1), (20, 6, 2),
       (19, 7, 1), (20, 8, 2),
       (21, 5, 1), (22, 6, 2),
       (21, 7, 1), (22, 8, 2);

CREATE TABLE IF NOT EXISTS grades
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    subject_id INT NOT NULL,
    grade      INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subjects_i18n (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO grades(subject_id, grade)
VALUES (1, 195), (3, 186), (5, 174), (7, 155),
       (2, 195), (4, 186), (6, 174), (8, 155),
       (1, 169), (5, 139), (7, 154),
       (2, 169), (6, 139), (8, 154),
       (5, 181), (7, 149),
       (6, 181), (8, 149),
       (1, 158), (5, 178), (7, 169), (9, 183),
       (2, 158), (6, 178), (8, 169), (10, 183),
       (1, 196), (3, 145), (5, 179), (7, 148),
       (2, 196), (4, 145), (6, 179), (8, 148),
       (5, 172), (7, 139),
       (6, 172), (8, 139),
       (1, 141), (5, 128), (7, 198),
       (2, 141), (6, 128), (8, 198),
       (1, 165), (5, 147), (7, 135),  (9, 161),
       (2, 165), (6, 147), (8, 135), (10, 161),
       (1, 147), (5, 158), (7, 123), (9, 147),
       (2, 147), (6, 158), (8, 123), (10, 147),
       (1, 147), (3, 159), (5, 148), (7, 126),
       (2, 147), (4, 159), (6, 148), (8, 126),
       (5, 186), (7, 173),
       (6, 186), (8, 173),
       (5, 148), (7, 165),
       (6, 148), (8, 165),
       (1, 124), (5, 158), (7, 161),
       (2, 124), (6, 158), (8, 161),
       (1, 194), (5, 158), (7, 173), (9, 145),
       (2, 194), (6, 158), (8, 173), (10, 145),
       (5, 177), (7, 157),
       (6, 177), (8, 157),
       (1, 158), (3, 163), (5, 147), (7, 129),
       (2, 158), (4, 163), (6, 147), (8, 129),
       (1, 138), (5, 144), (7, 187),
       (2, 138), (6, 144), (8, 187),
       (1, 166), (3, 155), (5, 144), (7, 133),
       (2, 166), (4, 155), (6, 144), (8, 133),
       (5, 189), (7, 191),
       (6, 189), (8, 191),
       (1, 148), (5, 184), (7, 157),
       (2, 148), (6, 184), (8, 157),
       (1, 144), (5, 178), (7, 163), (9, 155),
       (2, 144), (6, 178),  (8, 163), (10, 155),
       (1, 169), (5, 158), (7, 189),
       (2, 169), (6, 158), (8, 189),
       (1, 194), (5, 178), (7, 166), (9, 139),
       (2, 194), (6, 178), (8, 166), (10, 139);

CREATE TABLE IF NOT EXISTS applications_grades
(
    application_id INT NOT NULL,
    grade_id       INT NOT NULL,
    FOREIGN KEY (application_id) REFERENCES applications (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (grade_id) REFERENCES grades (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO applications_grades(application_id, grade_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4),
       (2, 5), (2, 6), (2, 7), (2, 8),
       (3, 9), (3, 10), (3, 11),
       (4, 12), (4, 13), (4, 14),
       (5, 15), (5, 16),
       (6, 17), (6, 18),
       (7, 19), (7, 20), (7, 21), (7, 22),
       (8, 23), (8, 24), (8, 25), (8, 26),
       (9, 27), (9, 28), (9, 29), (9, 30),
       (10, 31), (10, 32), (10, 33), (10, 34),
       (11, 35), (11, 36),
       (12, 37), (12, 38),
       (13, 39), (13, 40), (13, 41),
       (14, 42), (14, 43), (14, 44),
       (15, 45), (15, 46), (15, 47), (15, 48),
       (16, 49), (16, 50), (16, 51), (16, 52),
       (17, 53), (17, 54), (17, 55), (17, 56),
       (18, 57), (18, 58), (18, 59), (18, 60),
       (19, 61), (19, 62), (19, 63), (19, 64),
       (20, 65), (20, 66), (20, 67), (20, 68),
       (21, 69), (21, 70),
       (22, 71), (22, 72),
       (23, 73), (23, 74),
       (24, 75), (24, 76),
       (25, 77), (25, 78), (25, 79),
       (26, 80), (26, 81), (26, 82),
       (27, 83), (27, 84), (27, 85), (27, 86),
       (28, 87), (28, 88), (28, 89), (28, 90),
       (29, 91), (29, 92),
       (30, 93), (30, 94),
       (31, 95), (31, 96), (31, 97), (31, 98),
       (32, 99), (32, 100), (32, 101),  (32, 102),
       (33, 103),  (33, 104), (33, 105),
       (34, 106), (34, 107), (34, 108),
       (35, 109), (35, 110), (35, 111), (35, 112),
       (36, 113), (36, 114), (36, 115), (36, 116),
       (37, 117), (37, 118),
       (38, 119), (38, 120),
       (39, 121), (39, 122), (39, 123),
       (40, 124), (40, 125), (40, 126),
       (41, 127), (41, 128), (41, 129), (41, 130),
       (42, 131), (42, 132), (42, 133), (42, 134),
       (43, 135), (43, 136), (43, 137),
       (44, 138), (44, 139), (44, 140),
       (45, 141), (45, 142), (45, 143), (45, 144),
       (46, 145), (46, 146), (46, 147), (46, 148);