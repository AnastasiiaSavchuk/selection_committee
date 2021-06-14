USE selection_committee;
# applicant
# INSERT_APPLICANT_USER_FIELDS 
INSERT INTO user (email, password, role_id)
SELECT 'LOomova@gmailcom', '123456', id
FROM role
WHERE role = 'USER';

# INSERT_APPLICANT_FULL_FIELDS 
INSERT INTO applicant(user_id, first_name, middle_name, last_name, city, region, school_name, certificate, is_blocked)
VALUES (12, 'Olena', 'Volodymyrivna', 'Lomova', 'Lvivdfsdfo', 'Lviv region', 'School №34', '', 0);

UPDATE applicant
SET certificate = 1
WHERE user_id = 1;

# GET_ALL_APPLICANT
SELECT a.user_id,
       u.email,
       u.password,
       u.role_id,
       r.role,
       a.first_name,
       a.middle_name,
       a.last_name,
       a.city,
       a.region,
       a.school_name,
       a.is_blocked
FROM applicant a,
     user u,
     role r
WHERE a.user_id = u.id
  AND u.role_id = r.id;

# GET_APPLICANT_BY_ID
SELECT u.id,
       a.user_id,
       u.email,
       u.password,
       u.role_id,
       r.role,
       a.first_name,
       a.middle_name,
       a.last_name,
       a.city,
       a.region,
       a.school_name,
       a.is_blocked
FROM user u
         INNER JOIN applicant a ON u.id = a.user_id,
     role r
WHERE u.role_id = r.id
  and u.id = 12;

SELECT a.certificate
FROM user u
         INNER JOIN applicant a ON u.id = a.user_id
WHERE user_id = 12;

# GET_APPLICANT_BY_EMAIL
SELECT u.id, u.email, u.password, r.role
FROM user u,
     role r
WHERE u.role_id = r.id
  AND u.email = 'LOomova@gmailcom';

# UPDATE_APPLICANT 
UPDATE user u, applicant a
SET u.email       = 'lomova@gmailcom',
    u.password    = '654123',
    a.first_name  = 'Олена',
    a.middle_name = 'Володимирівна',
    a.last_name   = 'Ломова',
    a.city        = 'Львів',
    a.region      = 'Львівська область',
    a.school_name = 'Школа №56'
WHERE u.id = a.user_id
  AND a.user_id = 12;

# UPDATE_APPLICANT_BY_ADMIN
UPDATE applicant
SET is_blocked = 1
WHERE user_id = 12;

# DELETE_APPLICANT
DELETE
FROM user
WHERE id = 12;

# subject
# INSERT_SUBJECT
INSERT INTO subject (passing_grade)
VALUES (155);

# INSERT_SUBJECT_TRANSLATION
INSERT INTO subject_translation (subject_id, language_id, subject)
VALUES ((SELECT MAX(6) FROM subject), (SELECT id FROM language WHERE lang_code = 'en'), 'Computer science'),
       ((SELECT MAX(6) FROM subject), (SELECT id FROM language WHERE lang_code = 'uk'), 'лалала');

# GET_ALL_SUBJECTS
SELECT s.id, s.passing_grade, st.subject
FROM subject s
         INNER JOIN subject_translation st ON s.id = st.subject_id
         INNER JOIN language l ON l.id = st.language_id
WHERE l.lang_code = 'uk';

# GET_SUBJECTS_BY_FACULTY_ID
SELECT s.id, s.passing_grade, st.subject
FROM faculty f,
     faculty_subject fs,
     subject s,
     subject_translation st,
     language l
WHERE f.id = fs.faculty_id
  AND fs.subject_id = s.id
  AND s.id = st.subject_id
  AND st.language_id = l.id
  AND f.id = 1
  AND l.lang_code = 'uk';

# GET_SUBJECT_BY_ID
SELECT s.id, s.passing_grade, GROUP_CONCAT(st.subject SEPARATOR ' / ') as subject
FROM subject s
         INNER JOIN subject_translation st ON s.id = st.subject_id
         INNER JOIN language l ON l.id = st.language_id
WHERE s.id = 4
GROUP BY s.id;

# UPDATE_SUBJECT
UPDATE subject
SET passing_grade = 135
WHERE id = 6;

# UPDATE_SUBJECT_TRANSLATION
UPDATE subject_translation
SET subject = 'Інформатика'
WHERE subject_id = 6
  AND language_id = 2;

# DELETE_SUBJECT
DELETE
FROM subject
WHERE id = 6;

# faculty
#INSERT_FACULTY
INSERT INTO faculty (budget_qty, total_qty)
VALUES (6, 3);

# INSERT_FACULTY_TRANSLATION
INSERT INTO faculty_translation (faculty_id, language_id, faculty)
VALUES ((SELECT MAX(5) FROM faculty), (SELECT id FROM language WHERE lang_code = 'en'), 'Faculty of History'),
       ((SELECT MAX(5) FROM faculty), (SELECT id FROM language WHERE lang_code = 'uk'), 'Історичний факультет');

#INSERT_FACULTY_SUBJECT
INSERT INTO faculty_subject(faculty_id, subject_id)
VALUES (5, 3);
INSERT INTO faculty_subject(faculty_id, subject_id)
VALUES (5, 5);

# GET_FACULTY_BY_ID
SELECT f.id, f.average_passing_grade, f.budget_qty, f.total_qty, ft.faculty
FROM faculty f
         INNER JOIN faculty_translation ft ON f.id = ft.faculty_id
         INNER JOIN language l ON l.id = ft.language_id
WHERE f.id = 1
  AND l.lang_code = 'uk';

# GET_FACULTY_TO_UPDATE
SELECT f.id,
       GROUP_CONCAT(ft.faculty SEPARATOR ' / ') as faculty,
       f.budget_qty,
       f.total_qty,
       f.average_passing_grade
FROM faculty f
         INNER JOIN faculty_translation ft ON f.id = ft.faculty_id
         INNER JOIN language l ON l.id = ft.language_id
WHERE f.id = 1
GROUP BY f.id;

# GET_ALL_FACULTIES
SELECT f.id, f.average_passing_grade, f.budget_qty, f.total_qty, ft.faculty
FROM faculty f,
     faculty_translation ft
         INNER JOIN language l ON ft.language_id = l.id
WHERE f.id = ft.faculty_id
  AND l.lang_code = 'uk';

# UPDATE_FACULTY
UPDATE faculty
SET budget_qty = 4,
    total_qty  = 9
WHERE id = 5;

# UPDATE_FACULTY_TRANSLATION
UPDATE faculty_translation
SET faculty = 'Факультет історії'
WHERE faculty_id = 5
  AND language_id = 2;

# DELETE_FACULTY
DELETE
FROM faculty
WHERE id = 5;

# application
# INSERT_APPLICATION 
INSERT INTO application (user_id, faculty_id, is_sent, application_status_id)
SELECT 2, 2, false, id
FROM application_status
WHERE status = 'IN_PROCESSING';

# GET_APPLICATION_BY_ID
SELECT ap.id,
       ap.user_id,
       u.email,
       a.last_name,
       a.first_name,
       a.middle_name,
       ap.faculty_id,
       ft.faculty,
       ap.sum_of_grades,
       ap.average_grade,
       ap.is_sent,
       ap.application_status_id
FROM application ap,
     user u,
     applicant a,
     faculty_translation ft
         INNER JOIN language l ON l.id = ft.language_id
WHERE a.user_id = ap.user_id
  AND u.id = ap.user_id
  AND ft.faculty_id = ap.faculty_id
  AND ap.id = 2
  AND l.lang_code = 'uk';

# GET_APPLICATIONS_BY_USER_ID
SELECT ap.id,
       ap.user_id,
       u.email,
       a.last_name,
       a.first_name,
       a.middle_name,
       a.is_blocked,
       ap.faculty_id,
       ft.faculty,
       ap.sum_of_grades,
       ap.average_grade,
       ap.is_sent,
       ap.application_status_id
FROM application ap,
     user u,
     applicant a,
     faculty_translation ft
         INNER JOIN language l ON l.id = ft.language_id
WHERE a.user_id = ap.user_id
  AND u.id = ap.user_id
  AND ft.faculty_id = ap.faculty_id
  AND ap.user_id = 2
  AND l.lang_code = 'uk';

# GET_APPLICATIONS_BY_FACULTY_ID 
SELECT ap.id,
       ap.user_id,
       u.email,
       a.last_name,
       a.first_name,
       a.middle_name,
       a.is_blocked,
       ap.faculty_id,
       ft.faculty,
       ap.sum_of_grades,
       ap.average_grade,
       ap.is_sent,
       ap.application_status_id
FROM application ap,
     user u,
     applicant a,
     faculty_translation ft
         INNER JOIN language l ON l.id = ft.language_id
WHERE a.user_id = ap.user_id
  AND u.id = ap.user_id
  AND ft.faculty_id = ap.faculty_id
  AND ap.faculty_id = 2
  AND l.lang_code = 'uk'
ORDER BY ap.average_grade DESC;

# UPDATE_APPLICATION 
UPDATE application
SET application_status_id = (SELECT id FROM application_status WHERE status = 'REJECTED')
WHERE id = 5;

# DELETE_APPLICATION
DELETE
FROM application
WHERE id = 3;

#IS_EXIST
SELECT *
FROM application
WHERE user_id = 3
  AND faculty_id = 2;

# grade
# INSERT_GRADE
INSERT INTO grade(subject_id, grade)
VALUES (5, 156);
INSERT INTO grade(subject_id, grade)
VALUES (4, 167);
INSERT INTO grade(subject_id, grade)
VALUES (3, 196);

# INSERT_APPLICATION_GRADE
INSERT INTO application_grade(application_id, grade_id)
VALUES (24, 40);
INSERT INTO application_grade(application_id, grade_id)
VALUES (24, 41);

# GET_GRADES_BY_APPLICATION_ID
SELECT g.id as grade_id, s.id as subject_id, st.subject, s.passing_grade, g.grade
FROM grade g
         INNER JOIN application_grade ag ON ag.grade_id = g.id
         INNER JOIN subject s ON s.id = g.subject_id
         INNER JOIN subject_translation st ON st.subject_id = s.id
         INNER JOIN language l ON l.id = st.language_id
WHERE ag.application_id = 15
  AND l.lang_code = 'en';

# DELETE_GRADE 
DELETE
FROM grade
WHERE id = 60;

SELECT AVG(s.passing_grade)
FROM subject s
         INNER JOIN faculty_subject fs ON fs.subject_id = s.id
         INNER JOIN faculty f on fs.faculty_id = f.id