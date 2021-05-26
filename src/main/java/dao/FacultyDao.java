package dao;

import domain.Faculty;
import util.AbstractCRUDOperations;

import java.util.List;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {
    /**
     * Insert the faculty's details into DB.
     *
     * @param faculty is the faculty to insert.
     * @param locales is the locales data.
     */
    void createFacultyTranslation(Faculty faculty, List<String> locales);

    /**
     * Updates the faculty's details in the DB.
     *
     * @param faculty is the faculty to update.
     */
    void updateFaculty(Faculty faculty);
}
