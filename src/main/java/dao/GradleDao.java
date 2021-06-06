package dao;

import domain.Application;
import domain.Grade;

import java.util.List;

public interface GradleDao {
    /**
     * Insert grades into DB.
     *
     * @param grade to insert.
     * @return single entity
     */
    Grade createGrade(Grade grade);

    /**
     * Insert grades to the application into DB.
     *
     * @param application entity to insert.
     */
    boolean createApplicationGrade(Application application);

    /**
     * Get list of all grades details by user id from DB.
     *
     * @param applicationId entity details id.
     * @param locales       locales data.
     * @return list of grades.
     */
    List<Grade> readGradesByApplicationId(int applicationId, List<String> locales);

    /**
     * Delete grade based id from DB.
     *
     * @param id entity details id.
     */
    boolean delete(int id);
}
