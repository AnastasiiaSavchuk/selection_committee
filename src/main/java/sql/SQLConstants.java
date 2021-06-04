package sql;

public class SQLConstants {
    //applicant
    public static final String INSERT_APPLICANT_USER_FIELDS = "INSERT INTO user (email, password, role_id) " +
            "SELECT ?, ?, id FROM role WHERE role = 'USER'";
    public static final String INSERT_APPLICANT_FULL_FIELDS = "INSERT INTO applicant(user_id, first_name, middle_name, " +
            "last_name, city, region, school_name, certificate, is_blocked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_ALL_APPLICANT = "SELECT a.user_id, u.email, u.password, u.role_id, r.role, a.first_name, " +
            "a.middle_name, a.last_name, a.city, a.region, a.school_name, a.is_blocked " +
            "FROM applicant a,  user u, role r " +
            "WHERE a.user_id = u.id AND u.role_id = r.id";
    public static final String GET_APPLICANT_BY_ID = "SELECT u.id, a.user_id, u.email, u.password, u.role_id, r.role, " +
            "a.first_name, a.middle_name, a.last_name, a.city, a.region, a.school_name, a.is_blocked " +
            "FROM user u INNER JOIN applicant a ON u.id = a.user_id, role r " +
            "WHERE u.role_id = r.id and u.id = ?";
    public static final String GET_CERTIFICATE = "SELECT a.certificate " +
            "FROM user u " +
            "INNER JOIN applicant a ON u.id = a.user_id " +
            "WHERE user_id = ?";
    public static final String GET_APPLICANT_BY_EMAIL = "SELECT u.id, u.email, u.password, r.role " +
            "FROM user u, role r " +
            "WHERE u.role_id = r.id AND u.email = ?";
    public static final String UPDATE_APPLICANT = "UPDATE user u, applicant a SET u.email = ?,u.password = ?, " +
            "a.first_name = ?, a.middle_name = ?, a.last_name = ?, a.city = ?, a.region = ?, a.school_name = ? " +
            "WHERE u.id = a.user_id AND a.user_id = ?";
    public static final String UPDATE_APPLICANT_BY_ADMIN = "UPDATE applicant SET is_blocked = ? WHERE user_id = ?";
    public static final String DELETE_APPLICANT = "DELETE FROM user WHERE id = ?";
    //subject
    public static final String INSERT_SUBJECT = "INSERT INTO subject (passing_grade) VALUES (?)";
    public static final String INSERT_SUBJECT_TRANSLATION = "INSERT INTO subject_translation (subject_id, language_id, subject) " +
            "VALUES ((SELECT MAX(id) FROM subject), (SELECT id FROM language WHERE lang_code = 'en'), ?), " +
            "((SELECT MAX(id) FROM subject), (SELECT id FROM language WHERE lang_code = 'uk'), ?)";
    public static final String GET_ALL_SUBJECTS = "SELECT s.id, st.subject, s.passing_grade " +
            "FROM subject s " +
            "INNER JOIN subject_translation st ON s.id = st.subject_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE l.lang_code = ?";
    public static final String GET_SUBJECTS_BY_FACULTY_ID = "SELECT s.id, s.passing_grade, st.subject " +
            "FROM faculty f, faculty_subject fs, subject s, subject_translation st, language l " +
            "WHERE f.id = fs.faculty_id AND fs.subject_id = s.id AND s.id = st.subject_id AND st.language_id = l.id AND f.id = ? AND l.lang_code = ?";
    public static final String GET_SUBJECT_BY_ID = "SELECT s.id, s.passing_grade, st.subject " +
            "FROM subject s " +
            "INNER JOIN subject_translation st ON s.id = st.subject_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE s.id = ?  AND l.lang_code = ?";
    public static final String GET_SUBJECT_TO_UPDATE = "SELECT s.id, s.passing_grade, GROUP_CONCAT(st.subject SEPARATOR ' / ') as subject " +
            "FROM subject s " +
            "INNER JOIN subject_translation st ON s.id = st.subject_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE s.id = ? GROUP BY s.id";
    public static final String UPDATE_SUBJECT = "UPDATE subject SET passing_grade = ? WHERE id = ?";
    public static final String UPDATE_SUBJECT_TRANSLATION = "UPDATE subject_translation SET subject = ? " +
            "WHERE subject_id = ? AND language_id = ?";
    public static final String DELETE_SUBJECT = "DELETE FROM subject WHERE id = ?";
    //faculty
    public static final String INSERT_FACULTY = "INSERT INTO faculty (budget_qty, total_qty) VALUES (?, ?)";
    public static final String INSERT_FACULTY_TRANSLATION = "INSERT INTO faculty_translation (faculty_id, language_id, faculty) " +
            "VALUES ((SELECT MAX(id) FROM faculty), (SELECT id FROM language WHERE lang_code = 'en'), ?), " +
            "((SELECT MAX(id) FROM faculty), (SELECT id FROM language WHERE lang_code = 'uk'), ?)";
    public static final String INSERT_FACULTY_SUBJECT = "INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (?, ?)";
    public static final String GET_ALL_FACULTIES = "SELECT f.id, f.budget_qty, f.total_qty, ft.faculty " +
            "FROM faculty f, faculty_translation ft " +
            "INNER JOIN language l ON ft.language_id = l.id " +
            "WHERE f.id = ft.faculty_id AND l.lang_code = ?";
    public static final String GET_FACULTY_BY_ID = "SELECT f.id, f.budget_qty, f.total_qty, ft.faculty " +
            "FROM faculty f " +
            "INNER JOIN faculty_translation ft ON f.id = ft.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE f.id = ? AND l.lang_code = ?";
    public static final String GET_FACULTY_TO_UPDATE = "SELECT f.id, f.budget_qty, f.total_qty, GROUP_CONCAT(ft.faculty SEPARATOR ' / ') as faculty " +
            "FROM faculty f " +
            "INNER JOIN faculty_translation ft ON f.id = ft.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE f.id = ?  GROUP BY f.id";
    public static final String UPDATE_FACULTY = "UPDATE faculty SET  budget_qty = ?, total_qty = ? WHERE id = ?";
    public static final String UPDATE_FACULTY_TRANSLATION = "UPDATE faculty_translation SET faculty = ? " +
            "WHERE faculty_id = ? AND language_id = ?";
    public static final String DELETE_FACULTY = "DELETE FROM faculty WHERE id = ?";
    //application
    public static final String INSERT_APPLICATION = "INSERT INTO application (user_id, faculty_id, applicationStatus_id) " +
            "SELECT ?, ?, id FROM application_status WHERE status = ?";
    public static final String GET_APPLICATION_BY_ID = "SELECT ap.id, ap.user_id, a.first_name, a.last_name, ap.faculty_id, " +
            "ft.faculty, ap.sum_of_grades, ap.average_grade, ap.applicationStatus_id " +
            "FROM application ap, applicant a, faculty_translation ft " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE a.user_id = ap.user_id AND ft.faculty_id = ap.faculty_id AND ap.id = ? AND l.lang_code = ?";
    public static final String GET_APPLICATIONS_BY_USER_ID = "SELECT ap.id, ap.user_id, a.first_name, a.last_name, " +
            "ap.faculty_id, ft.faculty, ap.sum_of_grades, ap.average_grade, ap.applicationStatus_id " +
            "FROM application ap, applicant a, faculty_translation ft " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE a.user_id = ap.user_id AND ft.faculty_id = ap.faculty_id AND ap.user_id = ? AND l.lang_code = ?";
    public static final String GET_APPLICATIONS_BY_FACULTY_ID = "SELECT ap.id, ap.user_id, a.first_name, a.last_name, " +
            "ap.faculty_id, ft.faculty, ap.sum_of_grades, ap.average_grade, ap.applicationStatus_id " +
            "FROM application ap, applicant a, faculty_translation ft " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE a.user_id = ap.user_id AND ft.faculty_id = ap.faculty_id AND ap.faculty_id = ? AND l.lang_code = ?";
    public static final String UPDATE_APPLICATION = "UPDATE application SET applicationStatus_id = " +
            "(SELECT id FROM application_status WHERE status = ?) WHERE id = ?";
    public static final String DELETE_APPLICATION = "DELETE FROM application WHERE id = ?";
    //grade
    public static final String INSERT_GRADE = "INSERT INTO grade(subject_id, grade) VALUES(?, ?)";
    public static final String INSERT_APPLICATION_GRADE = "INSERT INTO application_grade(application_id, grade_id) VALUES (?, ?)";
    public static final String GET_GRADE_BY_ID = "SELECT g.id, s.id, st.subject, s.passing_grade, g.grade " +
            "FROM grade g INNER JOIN subject s ON s.id = g.subject_id " +
            "INNER JOIN subject_translation st ON st.subject_id = s.id " +
            "INNER JOIN language la ON la.id = st.language_id " +
            "WHERE g.id = ? AND la.lang_code = ?";
    public static final String GET_GRADES_BY_APPLICATION_ID = "SELECT g.id, s.id as subj_id, st.subject, s.passing_grade, g.grade " +
            "FROM grade g INNER JOIN application_grade ag ON ag.grade_id = g.id INNER JOIN subject s ON s.id = g.subject_id " +
            "INNER JOIN subject_translation st ON st.subject_id = s.id INNER JOIN language l ON l.id = st.language_id " +
            "WHERE ag.application_id = ? AND l.lang_code = ?";
    public static final String GET_GRADES_BY_USER_ID = "SELECT g.id, s.id as subj_id, st.subject, s.passing_grade, g.grade " +
            "FROM grade g " +
            "INNER JOIN user u ON g.user_id = u.id " +
            "INNER JOIN subject s ON s.id = g.subject_id " +
            "INNER JOIN subject_translation st ON st.subject_id = s.id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE u.id = ? AND l.lang_code = ?";
    public static final String DELETE_GRADE = "DELETE FROM grade WHERE id = ?";
}
