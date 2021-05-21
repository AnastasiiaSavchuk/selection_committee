package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Subject entity.
 *
 * @author A.Savchuk
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = 5489632147895423687L;
    private int id;
    private int passingGrade;
    private List<String> subjectList;

    public static Subject createSubject(int id, int passingGrade, List<String> subjects) {
        Subject newSubject = new Subject();
        newSubject.id = id;
        newSubject.passingGrade = passingGrade;
        newSubject.subjectList = new ArrayList<>();
        newSubject.subjectList.addAll(subjects);
        return newSubject;
    }

    public static Subject createSubject(int id, int passingGrade) {
        Subject newSubject = new Subject();
        newSubject.id = id;
        newSubject.passingGrade = passingGrade;
        return newSubject;
    }

    public static Subject createSubject(int id, List<String> subjects) {
        Subject newSubject = new Subject();
        newSubject.id = id;
        newSubject.subjectList = new ArrayList<>();
        newSubject.subjectList.addAll(subjects);
        return newSubject;
    }

    public static Subject createSubject(int passingGrade) {
        Subject newSubject = new Subject();
        newSubject.passingGrade = passingGrade;
        return newSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && passingGrade == subject.passingGrade && Objects.equals(subjectList, subject.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passingGrade, subjectList);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", passingGrade=" + passingGrade +
                ", subjectList=" + subjectList +
                '}';
    }
}
