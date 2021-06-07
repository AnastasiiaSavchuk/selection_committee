package dao;

import domain.Applicant;
import domain.Application;
import domain.Faculty;

import java.util.List;

public interface StatementDao {
    /**
     * The method get average grade for the application and compare it to min grade level enough
     * to be approved to budget or contract.
     *  @param applicationList list of application List to reach budget and contract places
     * @param faculty         entity to get passing grade
     */
    void changeApplicationStatus(List<Application> applicationList, Faculty faculty);

    /**
     * Update application's status in the db.
     *
     * @param applicationsList list of applications.
     * @return true if succeed.
     */
    boolean updateApplicationStatusByQTY(List<Application> applicationsList);

    /**
     * Update application's status to IN_PROCESSING in the db.
     *
     * @param applicationsList list of applications.
     * @return true if succeed.
     */
    void rollbackApplicationStatusToInitial(List<Application> applicationsList);

    /**
     * Check that current applicant hasn't apply to the current faculty.
     *
     * @param applicationsList list of applications.
     * @return true if exist or false if not exist.
     */
    boolean isExist(List<Application> applicationsList);
}
