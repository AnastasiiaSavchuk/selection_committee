package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Faculty {
    private int id;
    private String name;
    private int budgetQty;
    private int totalQty;
    private List<Subject> subjectList;
    List<String> local;

    public static Faculty createFaculty(String name, int budgetQty, int totalQty, List<Subject> subjectList, String[] locals) {
        Faculty newFaculty = new Faculty();
        newFaculty.name = name;
        newFaculty.budgetQty = budgetQty;
        newFaculty.totalQty = totalQty;
        newFaculty.subjectList = new ArrayList<>();
        newFaculty.subjectList.addAll(subjectList);
        newFaculty.local = new ArrayList<>();
        newFaculty.local.addAll(Arrays.asList(locals));
        return newFaculty;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && budgetQty == faculty.budgetQty && totalQty == faculty.totalQty && Objects.equals(name, faculty.name) && Objects.equals(subjectList, faculty.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budgetQty, totalQty, subjectList);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budgetQty=" + budgetQty +
                ", totalQty=" + totalQty +
                ", subjectList=" + subjectList +
                '}';
    }
}
