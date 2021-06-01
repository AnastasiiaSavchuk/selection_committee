package domain;

import java.io.Serializable;
import java.util.Comparator;
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

    public static Comparator<Subject> COMPARE_BY_ID = Comparator.comparing(Subject::getId);

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
                ", subject=" + subjectList +
                ", passingGrade=" + passingGrade +
                '}';
    }
}
