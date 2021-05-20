package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Faculty implements Serializable {
    private static final long serialVersionUID = 6985471254800359845L;
    private int id;
    private String faculty;
    private int budgetQty;
    private int totalQty;
    private List<Subject> subjectList;
    List<String> locales;

    public static Faculty createFaculty(int id, String faculty, int budgetQty, int totalQty, List<Subject> subjects, String[] localesName) {
        Faculty newFaculty = new Faculty();
        newFaculty.id = id;
        newFaculty.faculty = faculty;
        newFaculty.budgetQty = budgetQty;
        newFaculty.totalQty = totalQty;
        newFaculty.subjectList = new ArrayList<>();
        newFaculty.subjectList.addAll(subjects);
        newFaculty.locales = new ArrayList<>();
        newFaculty.locales.addAll(Arrays.asList(localesName));
        return newFaculty;
    }

    public static Faculty createFaculty(String faculty, int budgetQty, int totalQty, List<Subject> subjects, String[] localesName) {
        Faculty newFaculty = new Faculty();
        newFaculty.faculty = faculty;
        newFaculty.budgetQty = budgetQty;
        newFaculty.totalQty = totalQty;
        newFaculty.subjectList = new ArrayList<>();
        newFaculty.subjectList.addAll(subjects);
        newFaculty.locales = new ArrayList<>();
        newFaculty.locales.addAll(Arrays.asList(localesName));
        return newFaculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getBudgetQty() {
        return budgetQty;
    }

    public void setBudgetQty(int budgetQty) {
        this.budgetQty = budgetQty;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
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
        Faculty faculty1 = (Faculty) o;
        return id == faculty1.id && budgetQty == faculty1.budgetQty && totalQty == faculty1.totalQty
                && Objects.equals(faculty, faculty1.faculty) && Objects.equals(subjectList, faculty1.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, budgetQty, totalQty, subjectList);
    }

    @Override
    public String toString() {
        return "Faculty : " +
                "id : " + id +
                ", faculty : " + faculty +
                ", budgetQty : " + budgetQty +
                ", totalQty : " + totalQty +
                ", subjectList : " + subjectList +
                ", locales : " + locales + ';';
    }
}
