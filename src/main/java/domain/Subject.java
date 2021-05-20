package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject implements Serializable {
    private static final long serialVersionUID = 5489632147895423687L;
    private int id;
    private String subject;
    private int passingGrade;
    List<String> locales;

    public static Subject createGrade(int id, String subject, int passingGrade, List<String> localesNames) {
        Subject newSubject = new Subject();
        newSubject.id = id;
        newSubject.subject = subject;
        newSubject.passingGrade = passingGrade;
        newSubject.locales = new ArrayList<>();
        newSubject.locales.addAll(localesNames);
        return newSubject;
    }

    public static Subject createGrade(String subject, int passingGrade, List<String> localesNames) {
        Subject newSubject = new Subject();
        newSubject.subject = subject;
        newSubject.passingGrade = passingGrade;
        newSubject.locales = new ArrayList<>();
        newSubject.locales.addAll(localesNames);
        return newSubject;
    }

    public int getId() {
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

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    public List<String> getLocales() {
        return locales;
    }

    public void setLocales(List<String> locales) {
        this.locales = locales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject1 = (Subject) o;
        return id == subject1.id && passingGrade == subject1.passingGrade && Objects.equals(subject, subject1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, passingGrade);
    }

    @Override
    public String toString() {
        return "Subject : " +
                "id : " + id +
                ", subject : " + subject +
                ", passingGrade : " + passingGrade +
                ", locales : " + locales + ';';
    }
}
