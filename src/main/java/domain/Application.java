package domain;

import domain.enums.ApplicationStatus;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Application entity.
 *
 * @author A.Savchuk.
 */
public class Application implements Serializable {
    private static final long serialVersionUID = 2365841213412352526L;
    private int id;
    private Applicant applicant;
    private Faculty faculty;
    private int sumOfGrades;
    private int averageGrade;
    private ApplicationStatus applicationStatus;
    private List<Grade> gradeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getSumOfGrades() {
        return sumOfGrades;
    }

    public void setSumOfGrades(int sumOfGrades) {
        this.sumOfGrades = sumOfGrades;
    }

    public int getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(int averageGrade) {
        this.averageGrade = averageGrade;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradesList) {
        this.gradeList = gradesList;
    }

    public static Comparator<Application> COMPARE_BY_ID = Comparator.comparing(Application::getId);

    public static Comparator<Application> COMPARE_BY_AVERAGE_GRADE = Comparator.comparing(Application::getAverageGrade);

    public static Comparator<Application> COMPARE_BY_FACULTY_ID = Comparator.comparingInt(a -> a.getFaculty().getId());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return id == that.id && sumOfGrades == that.sumOfGrades && averageGrade == that.averageGrade &&
                Objects.equals(applicant, that.applicant) && Objects.equals(faculty, that.faculty) &&
                applicationStatus == that.applicationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicant, faculty, sumOfGrades, averageGrade, applicationStatus);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", faculty=" + faculty +
                ", sumOfGrades=" + sumOfGrades +
                ", averageGrade=" + averageGrade +
                ", applicationStatus=" + applicationStatus +
                ", gradeList=" + gradeList +
                '}';
    }
}
