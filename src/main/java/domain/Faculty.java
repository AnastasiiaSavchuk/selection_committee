package domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Faculty entity.
 *
 * @author A.Savchuk.
 */
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1458756966523100254L;
    private int id;
    private int budgetQty;
    private int totalQty;
    private int averagePassingGrade;
    private List<String> facultyList;
    private List<Subject> subjectList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAveragePassingGrade() {
        return averagePassingGrade;
    }

    public void setAveragePassingGrade(int averagePassingGrade) {
        this.averagePassingGrade = averagePassingGrade;
    }

    public List<String> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<String> facultyList) {
        this.facultyList = facultyList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public static Comparator<Faculty> COMPARE_BY_ID = Comparator.comparing(Faculty::getId);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && budgetQty == faculty.budgetQty && totalQty == faculty.totalQty && averagePassingGrade == faculty.averagePassingGrade && Objects.equals(facultyList, faculty.facultyList) && Objects.equals(subjectList, faculty.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetQty, totalQty, averagePassingGrade, facultyList, subjectList);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", budgetQty=" + budgetQty +
                ", totalQty=" + totalQty +
                ", averagePassingGrade=" + averagePassingGrade +
                ", facultyList=" + facultyList +
                ", subjectList=" + subjectList +
                '}';
    }
}
