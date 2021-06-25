package sql;

public class SQLConstants {
    //applicant
    public static final String INSERT_APPLICANT_LOGIN_FIELDS = "INSERT INTO user (email, password, role_id) " +
            "SELECT ?, ?, id FROM role WHERE role = 'USER'";
    public static final String INSERT_APPLICANT_FULL_FIELDS = "INSERT INTO applicant(user_id, first_name, middle_name, " +
            "last_name, city, region, school_name, certificate, is_blocked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_ALL_APPLICANT = "SELECT a.user_id, u.email, u.password, u.role_id, r.role, a.first_name, " +
            "a.middle_name, a.last_name, a.city, a.region, a.school_name, a.is_blocked " +
            "FROM applicant a " +
            "INNER JOIN user u ON a.user_id = u.id " +
            "INNER JOIN role r ON u.role_id = r.id ORDER BY a.user_id";
    public static final String GET_APPLICANT_BY_ID = "SELECT a.user_id, u.email, u.password, u.role_id, r.role, a.first_name, " +
            "a.middle_name, a.last_name, a.city, a.region, a.school_name, a.is_blocked " +
            "FROM user u " +
            "INNER JOIN applicant a ON a.user_id = u.id " +
            "INNER JOIN role r ON u.role_id = r.id " +
            "WHERE u.id = ?";
    public static final String GET_APPLICANT_BY_EMAIL = "SELECT u.id, u.email, u.password, r.role " +
            "FROM user u " +
            "INNER JOIN role r ON u.role_id = r.id " +
            "WHERE u.email = ?";
    public static final String GET_CERTIFICATE = "SELECT a.certificate " +
            "FROM user u " +
            "INNER JOIN applicant a ON u.id = a.user_id " +
            "WHERE user_id = ?";
    public static final String UPDATE_CERTIFICATE = "UPDATE applicant SET certificate = ? WHERE user_id = ?";
    public static final String UPDATE_APPLICANT = "UPDATE user u, applicant a SET u.email = ?,u.password = ?, a.first_name = ?, " +
            "a.middle_name = ?, a.last_name = ?, a.city = ?, a.region = ?, a.school_name = ? " +
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
            "WHERE l.lang_code = ? ORDER BY s.id";
    public static final String GET_SUBJECTS_BY_FACULTY_ID = "SELECT s.id, s.passing_grade, st.subject " +
            "FROM subject s " +
            "INNER JOIN faculty_subject fs on s.id = fs.subject_id " +
            "INNER JOIN faculty f ON f.id = fs.faculty_id " +
            "INNER JOIN subject_translation st ON s.id = st.subject_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE f.id = ? AND l.lang_code = ? ORDER BY s.id";
    public static final String GET_SUBJECT_BY_ID = "SELECT s.id, s.passing_grade, st.subject " +
            "FROM subject s " +
            "INNER JOIN subject_translation st ON s.id = st.subject_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE s.id = ? AND l.lang_code = ?";
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
    public static final String GET_FACULTY_BY_ID = "SELECT f.id, f.average_passing_grade, f.total_qty, f.budget_qty, ft.faculty " +
            "FROM faculty f " +
            "INNER JOIN faculty_translation ft ON f.id = ft.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE f.id = ? AND l.lang_code = ?";
    public static final String GET_FACULTY_TO_UPDATE = "SELECT f.id, f.average_passing_grade, f.total_qty, f.budget_qty, " +
            "GROUP_CONCAT(ft.faculty SEPARATOR ' / ') as faculty " +
            "FROM faculty f " +
            "INNER JOIN faculty_translation ft ON f.id = ft.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE f.id = ? GROUP BY f.id";
    public static final String GET_ALL_FACULTIES = "SELECT f.id, ft.faculty, f.budget_qty, f.total_qty, f.average_passing_grade " +
            "FROM faculty f " +
            "INNER JOIN faculty_translation ft on f.id = ft.faculty_id " +
            "INNER JOIN language l ON ft.language_id = l.id " +
            "WHERE l.lang_code = ? ORDER BY f.id";
    public static final String UPDATE_FACULTY = "UPDATE faculty SET  budget_qty = ?, total_qty = ? WHERE id = ?";
    public static final String UPDATE_FACULTY_TRANSLATION = "UPDATE faculty_translation SET faculty = ? " +
            "WHERE faculty_id = ? AND language_id = ?";
    public static final String DELETE_FACULTY = "DELETE FROM faculty WHERE id = ?";
    //application
    public static final String INSERT_APPLICATION = "INSERT INTO application (user_id, faculty_id, is_sent, application_status_id) " +
            "SELECT ?, ?, ?, id FROM application_status WHERE status = ?";
    public static final String GET_APPLICATION_BY_ID = "SELECT ap.id, ap.user_id, u.email, a.last_name, a.first_name, a.middle_name, " +
            " a.is_blocked, ft.faculty, ap.sum_of_grades, ap.average_grade, ap.is_sent, ap.application_status_id " +
            "FROM application ap " +
            "INNER JOIN applicant a ON a.user_id = ap.user_id " +
            "INNER JOIN user u on a.user_id = u.id " +
            "INNER JOIN faculty_translation ft ON ft.faculty_id = ap.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE ap.id = ? AND l.lang_code = ? ORDER BY ap.average_grade DESC";
    public static final String GET_APPLICATIONS_BY_USER_ID = "SELECT ap.id, ap.user_id, u.email, a.last_name, a.first_name, " +
            "a.middle_name, a.is_blocked, ap.faculty_id, ft.faculty, ap.sum_of_grades, ap.average_grade, ap.is_sent, ap.application_status_id " +
            "FROM application ap " +
            "INNER JOIN applicant a ON a.user_id = ap.user_id " +
            "INNER JOIN user u on a.user_id = u.id " +
            "INNER JOIN faculty_translation ft ON ft.faculty_id = ap.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE ap.user_id = ? AND l.lang_code = ? ORDER BY ap.average_grade DESC";
    public static final String GET_APPLICATIONS_BY_FACULTY_ID = "SELECT ap.id, ap.user_id, u.email, a.last_name, a.first_name, " +
            "a.middle_name, a.is_blocked, ap.faculty_id, ft.faculty, ap.sum_of_grades, ap.average_grade, ap.is_sent, ap.application_status_id " +
            "FROM application ap " +
            "INNER JOIN applicant a ON a.user_id = ap.user_id " +
            "INNER JOIN user u on a.user_id = u.id " +
            "INNER JOIN faculty_translation ft ON ft.faculty_id = ap.faculty_id " +
            "INNER JOIN language l ON l.id = ft.language_id " +
            "WHERE ap.faculty_id = ? AND l.lang_code = ? ORDER BY ap.average_grade DESC";
    public static final String UPDATE_APPLICATION = "UPDATE application SET application_status_id = " +
            "(SELECT id FROM application_status WHERE status = ?) WHERE id = ?";
    public static final String UPDATE_SEND_EMAIL = "UPDATE application SET is_sent = ? WHERE id = ?";
    public static final String DELETE_APPLICATION = "DELETE FROM application WHERE id = ?";
    public static final String IS_EXIST = "SELECT * FROM application WHERE user_id = ? AND faculty_id = ?";
    //grade
    public static final String INSERT_GRADE = "INSERT INTO grade(applicant_id, subject_id, grade) VALUES(?, ?, ?)";
    public static final String INSERT_APPLICATION_GRADE = "INSERT INTO application_grade(application_id, grade_id) VALUES (?, ?)";
    public static final String GET_GRADES_BY_APPLICANT_ID = "SELECT g.id as id, s.id as id, st.subject, s.passing_grade, g.grade " +
            "FROM grade g " +
            "INNER JOIN subject s ON s.id = g.subject_id " +
            "INNER JOIN subject_translation st ON st.subject_id = s.id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE g.applicant_id = ? AND l.lang_code = ? ORDER BY s.id";
    public static final String GET_APPLICANT_GRADE_BY_FACULTY_ID = "SELECT g.id as id, s.id as id, st.subject, s.passing_grade, g.grade " +
            "FROM grade g " +
            "INNER JOIN subject s ON s.id = g.subject_id " +
            "INNER JOIN subject_translation st ON st.subject_id = s.id " +
            "INNER JOIN faculty_subject fs on s.id = fs.subject_id " +
            "INNER JOIN faculty f ON f.id = fs.faculty_id " +
            "INNER JOIN language l ON l.id = st.language_id " +
            "WHERE f.id = ? " +
            "AND g.applicant_id = ? " +
            "AND l.lang_code = ? ORDER BY g.id";
    public static final String DELETE_GRADE = "DELETE FROM grade WHERE id = ?";
}
