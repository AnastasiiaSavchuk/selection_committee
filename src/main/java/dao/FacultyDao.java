package dao;

import domain.Faculty;
import util.AbstractCRUDOperations;

import java.util.List;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {
    /**
     * Insert the faculty's details into DB.
     *
     * @param faculty is the faculty to insert.
     */
    void createFacultyTranslation(Faculty faculty, List<String> locales);

    /**
     * Insert the subjects to the faculty in the DB.
     *
     * @param faculty is the faculty to insert.
     */
    void createFacultySubject(Faculty faculty);

    /**
     * Updates the faculty's details in the DB.
     *
     * @param faculty is the faculty to update.
     */
    void updateFacultyTranslation(Faculty faculty,List<String> locales);
}
