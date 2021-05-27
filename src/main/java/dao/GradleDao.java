package dao;

import domain.Grade;
import util.AbstractCRUDOperations;

import java.util.List;

public interface GradleDao extends AbstractCRUDOperations<Grade> {
    /**
     * Insert grades to the application into DB.
     *
     * @param applicationId entity details to insert.
     * @param grade         entity to insert.
     */
    void createApplicationGrade(int applicationId, Grade grade);

    List<Grade> readGradesByApplicationId(int applicationId, List<String> locales);
}
