package dao;

import domain.Applicant;
import domain.Application;
import domain.Faculty;
import util.AbstractCRUDOperations;

import java.util.List;


public interface ApplicationDao extends AbstractCRUDOperations<Application> {
    /**
     * Get list of all application details by user id from DB.
     *
     * @param userId  entity details id.
     * @param locales locales data.
     * @return list of entities.
     */
    List<Application> readApplicationsByUserId(int userId, List<String> locales);

    /**
     * Get list of all application details by faculty id from DB.
     *
     * @param facultyId entity details id.
     * @param locales   locales data.
     * @return list of entities.
     */
    List<Application> readApplicationByFacultyId(int facultyId, List<String> locales);

    /**
     * Check that current applicant hasn't apply to the current faculty.
     *
     * @param applicant entity details.
     * @param faculty   entity details.
     * @return true if exist or false if not exist.
     */
    boolean isExist(Applicant applicant, Faculty faculty);
}
