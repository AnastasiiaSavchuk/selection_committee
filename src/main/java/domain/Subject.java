package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Subject {
    private int id;
    private String subject;
    private int grade;
    List<String> local;

    public static Subject createGrade(String subject, int grade, String[] locals) {
        Subject newSubject = new Subject();
        newSubject.subject = subject;
        newSubject.grade = grade;
        newSubject.local = new ArrayList<>();
        newSubject.local.addAll(Arrays.asList(locals));
        return newSubject;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject1 = (Subject) o;
        return id == subject1.id && grade == subject1.grade && Objects.equals(subject, subject1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, grade);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", grade=" + grade +
                '}';
    }
}
