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
    private static final long serialVersionUID = 5874569853248012540L;
    private int id;
    private Applicant applicant;
    private Faculty faculty;
    private int sumOfGrades;
    private ApplicationStatus applicationStatus;
    private List<Grade> gradesList;

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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public List<Grade> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Grade> gradesList) {
        this.gradesList = gradesList;
    }

    public static Comparator<Application> COMPARE_BY_ID = Comparator.comparing(Application::getId);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return id == that.id && sumOfGrades == that.sumOfGrades && Objects.equals(applicant, that.applicant) &&
                Objects.equals(faculty, that.faculty) && applicationStatus == that.applicationStatus && Objects.equals(gradesList, that.gradesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicant, faculty, sumOfGrades, applicationStatus, gradesList);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", faculty=" + faculty +
                ", sumOfGrades=" + sumOfGrades +
                ", applicationStatus=" + applicationStatus +
                ", gradesList=" + gradesList +
                '}';
    }
}
