package sql;

public class SQLFields {
    //applicant
    public static final String USER_ID = "id";
    public static final String USER_ROLE = "role";
    public static final String APPLICANT_ID = "user_id";
    public static final String APPLICANT_EMAIL = "email";
    public static final String APPLICANT_PASSWORD = "password";
    public static final String APPLICANT_FIRST_NAME = "first_name";
    public static final String APPLICANT_MIDDLE_NAME = "middle_name";
    public static final String APPLICANT_LAST_NAME = "last_name";
    public static final String APPLICANT_CITY = "city";
    public static final String APPLICANT_REGION = "region";
    public static final String APPLICANT_SCHOOL_NAME = "school_name";
    public static final String APPLICANT_CERTIFICATE = "certificate";
    public static final String APPLICANT_IS_BLOCKED = "is_blocked";
    public static final String APPLICANT_ROLE = "role_id";
    //subject
    public static final String SUBJECT_ID = "id";
    public static final String SUBJECT_PASSING_GRADE = "passing_grade";
    public static final String SUBJECT = "subject";
    //faculty
    public static final String FACULTY_ID = "id";
    public static final String FACULTY = "faculty";
    public static final String FACULTY_BUDGET_QTY = "budget_qty";
    public static final String FACULTY_TOTAL_QTY = "total_qty";
    //application
    public static final String APPLICATION_ID = "id";
    public static final String APPLICATION_SUM_OF_GRADES = "sum_of_grades";
    public static final String STATUS_ID = "applicationStatus_id";
    //grade
    public static final String GRADE_ID = "id";
    public static final String GRADE = "grade";
}