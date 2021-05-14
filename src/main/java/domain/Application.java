package domain;

import domain.enums.ApplicationStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Application {
    private int id;
    private Applicant applicant;
    private Faculty faculty;
    private ApplicationStatus applicationStatus;
    List<String> localList;

    public static Application createApplication(Applicant applicant, Faculty faculty, ApplicationStatus applicationStatus, String[] locals) {
        Application newApplication = new Application();
        newApplication.applicant = applicant;
        newApplication.faculty = faculty;
        newApplication.applicationStatus = applicationStatus;
        newApplication.localList = new ArrayList<>();
        newApplication.localList.addAll(Arrays.asList(locals));
        return newApplication;
    }

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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public List<String> getLocalList() {
        return localList;
    }

    public void setLocalList(List<String> localList) {
        this.localList = localList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return id == that.id && Objects.equals(applicant, that.applicant) && Objects.equals(faculty, that.faculty) && applicationStatus == that.applicationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicant, faculty, applicationStatus);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", faculty=" + faculty +
                ", applicationStatus=" + applicationStatus +
                ", localList=" + localList +
                '}';
    }
}
