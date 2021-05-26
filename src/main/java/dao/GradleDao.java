package dao;

import domain.Application;
import domain.Grade;
import util.AbstractCRUDOperations;

import java.util.List;

public interface GradleDao extends AbstractCRUDOperations<Grade> {
    void create(Grade grade);

    /**
     * Insert grades to the application into DB.
     *
     * @param application entity to insert.
     */
    void createApplicationGrade(Application application);

    List<Grade> readGradesByApplicationId(int applicationId, List<String> locales);
}
