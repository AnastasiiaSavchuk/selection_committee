import dao.*;
import dao.impl.*;

public class Main {

    private static final ApplicantDao APPLICANT_DAO = new ApplicantDaoImpl();
    private static final SubjectDao SUBJECT_DAO = new SubjectDaoImpl();
    private static final FacultyDao FACULTY_DAO = new FacultyDaoImpl();
    private static final ApplicationDao APPLICATION_DAO = new ApplicationDaoImpl();
    private static final GradleDao GRADLE_DAO = new GradeDaoImpl();


    public static void main(String[] args) {
//        Applicant applicant1 = new Applicant();
//        applicant1.setId(12);
//        applicant1.setFirstName("Olena");
//        applicant1.setMiddleName("Volodymyrivna");
//        applicant1.setLastName("Lomova");
//        applicant1.setCity("Lvivdfsdfo");
//        applicant1.setRegion("Lviv region");
//        applicant1.setSchoolName("School №34");
//        applicant1.setCertificate(new byte[1]);
//        applicant1.setBlocked(false);
//
//        Applicant applicant2 = new Applicant();
//        applicant2.setId(12);
//        applicant2.setEmail("lomova@gmail.com");
//        applicant2.setPassword("654123");
//        applicant2.setFirstName("Olena");
//        applicant2.setMiddleName("Vasylivna");
//        applicant2.setLastName("Lomova");
//        applicant2.setCity("Lviv");
//        applicant2.setRegion("Lviv region");
//        applicant2.setSchoolName("School №92");
//        applicant2.setCertificate(new byte[1]);

//        APPLICANT_DAO.loginApplicant("LOomova@gmail.com","123654");
//        APPLICANT_DAO.create(applicant1);
//       APPLICANT_DAO.readAll(Arrays.asList("en"));
//        APPLICANT_DAO.readById(2, Arrays.asList("uk"));
//        APPLICANT_DAO.readByLogin("admin@gmail.com");
//        APPLICANT_DAO.update(applicant2);
//        APPLICANT_DAO.updateByAdmin(12, true);
//        APPLICANT_DAO.delete(12);
//
//        Subject subject1 = new Subject();
//        subject1.setPassingGrade(155);
//
//        Subject subject2 = new Subject();
//        subject2.setId(6);
//        subject2.setSubjectList(Arrays.asList("lalalala", "lilili"));
//
//        Subject subject3 = new Subject();
//        subject3.setId(6);
//        subject3.setPassingGrade(144);
//
//        Subject subject4 = new Subject();
//        subject4.setId(6);
//        subject4.setSubjectList(Arrays.asList("Computer Science", "Інформатика"));
//
//        SUBJECT_DAO.create(subject1);
//        SUBJECT_DAO.createSubjectTranslation(subject2);
//        SUBJECT_DAO.readAll(Arrays.asList("en"));
//        SUBJECT_DAO.readSubjectsByFacultyId(1, Arrays.asList("uk"));
//        SUBJECT_DAO.readById(6,Arrays.asList("en"));
//        SUBJECT_DAO.update(subject3);
//        SUBJECT_DAO.updateSubjectTranslation(subject4);
//        SUBJECT_DAO.delete(6);

//        Faculty faculty1 = new Faculty();
//        faculty1.setBudgetQty(3);
//        faculty1.setTotalQty(5);
//
//        Faculty faculty2 = new Faculty();
//        faculty2.setId(5);
//        faculty2.setFacultyList(Arrays.asList("lalala", "lilili"));
//
//        Faculty faculty3 = new Faculty();
//        faculty3.setId(5);
//        faculty3.setSubjectList(Arrays.asList(SUBJECT_DAO.readById(2, Arrays.asList("en")), SUBJECT_DAO.readById(6, Arrays.asList("en"))));
//
//        Faculty faculty4 = new Faculty();
//        faculty4.setId(5);
//        faculty4.setBudgetQty(5);
//        faculty4.setTotalQty(10);
//
//        Faculty faculty5 = new Faculty();
//        faculty5.setId(5);
//        faculty5.setFacultyList(Arrays.asList("Faculty of History", "Історичний факультет"));
//
//        FACULTY_DAO.create(faculty1);
//        FACULTY_DAO.createFacultyTranslation(faculty2);
//        FACULTY_DAO.createFacultySubject(faculty3);
//        FACULTY_DAO.readAll(Arrays.asList("uk"));
//        FACULTY_DAO.readById(5, Arrays.asList("en"));
//        FACULTY_DAO.update(faculty4);
//        FACULTY_DAO.updateFacultyTranslation(faculty5);
//        FACULTY_DAO.delete(5);

//        Application application1 = new Application();
//        application1.setApplicant(APPLICANT_DAO.readById(12, Arrays.asList("en")));
//        application1.setFaculty(FACULTY_DAO.readById(5, Arrays.asList("en")));
//        application1.setApplicationStatus(ApplicationStatus.NOT_PROCESSED);
//
//        Application application2 = new Application();
//        application2.setId(2);
//        application2.setApplicationStatus(ApplicationStatus.BLOCKED);
//
//        APPLICATION_DAO.create(application1);
//        APPLICATION_DAO.readApplicationsByUserId(2, Arrays.asList("uk"));
//        APPLICATION_DAO.readApplicationByFacultyId(1, Arrays.asList("en"));
//        APPLICATION_DAO.readById(2, Arrays.asList("en"));
//        APPLICATION_DAO.update(application2);
//        APPLICATION_DAO.delete(2);

//        Grade grade1 = new Grade();
//        grade1.setSubject(SUBJECT_DAO.readById(1, Arrays.asList("en")));
//        grade1.setGrade(175);
//
//        Grade grade2 = new Grade();
//        grade2.setId(59);
//
//        GRADLE_DAO.create(grade1);
//        GRADLE_DAO.createApplicationGrade(19, grade2);
//       GRADLE_DAO.readGradesByApplicationId(1, Arrays.asList("uk"));
//        GRADLE_DAO.readById(15, Arrays.asList("uk"));
//        GRADLE_DAO.delete(1);
    }
}
