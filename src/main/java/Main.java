import dao.*;
import dao.impl.*;
import domain.Applicant;
import domain.Subject;

import java.util.Arrays;

public class Main {

    private static final ApplicantDao APPLICANT_DAO = new ApplicantDaoImpl();
    private static final ApplicationDao APPLICATION_DAO = new ApplicationDaoImpl();
    private static final FacultyDao FACULTY_DAO = new FacultyDaoImpl();
    private static final GradleDao GRADLE_DAO = new GradeDaoImpl();
    private static final SubjectDao SUBJECT_DAO = new SubjectDaoImpl();

    public static void main(String[] args) {
//        APPLICANT_DAO.loginApplicant("LOomova@gmail.com", "123456");
//        APPLICANT_DAO.create(Applicant.createApplicant(12, "Olena", "Volodymyrivna", "Lomova", "Lvivdfsdfo", "Lviv region", "School №34", new byte[1], false), Arrays.asList("en", "uk"));
//        APPLICANT_DAO.readAll(Arrays.asList("en", "uk"));
//        APPLICANT_DAO.readById(12, Arrays.asList("en", "uk"));
//        APPLICANT_DAO.readByLogin("admin@gmail.com");
//        APPLICANT_DAO.update(Applicant.createApplicant(12, "lomova@gmail.com", "654321", "Olena", "Vasylivna", "Lomova", "Lviv", "Lviv region", "School №92", new byte[1]), Arrays.asList("en", "uk"));
//        APPLICANT_DAO.updateByAdmin(12, true);
//        APPLICANT_DAO.delete(12);

//        SUBJECT_DAO.create(Subject.createSubject(155), Arrays.asList("en", "uk"));//       SUBJECT_DAO.createSubjectTranslation((Subject.createSubject(6, Arrays.asList("lalalala", "lalala"))), Arrays.asList("en", "uk"));
//        SUBJECT_DAO.readAll(Arrays.asList("en"));
//        SUBJECT_DAO.getSubjectsByFacultyId(2, Arrays.asList("en"));
//        SUBJECT_DAO.readById(6, Arrays.asList("uk"));
//        SUBJECT_DAO.updateSubjectPassingGrade(Subject.createSubject(6, 135));
//        SUBJECT_DAO.update((Subject.createSubject(6, Arrays.asList("Faculty of History", "Історичний факультет"))), Arrays.asList("uk"));
//        SUBJECT_DAO.delete(6);

//        APPLICATION_DAO.create(Application.createApplication(), Arrays.asList("en", "uk"));
//        APPLICATION_DAO.readAll(Arrays.asList("en", "uk"));
//        APPLICATION_DAO.readById(20, Arrays.asList("en", "uk"));
//        APPLICATION_DAO.update(Application.createApplication(),Arrays.asList("en", "uk"));
//        APPLICATION_DAO.delete(20);

//        FACULTY_DAO.create(Faculty.createFaculty(), Arrays.asList("en", "uk");
//        FACULTY_DAO.readAll(Arrays.asList("en", "uk"));
//        FACULTY_DAO.readById(5,Arrays.asList("en", "uk"));
//        FACULTY_DAO.update(Faculty.createFaculty(),Arrays.asList("en", "uk"));
//        FACULTY_DAO.delete(1);

//        GRADLE_DAO.create();
//        GRADLE_DAO.readAll();
//        GRADLE_DAO.readById();
//        GRADLE_DAO.update();
//        GRADLE_DAO.delete();
    }
}
