package dao;

import domain.Application;
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
}
