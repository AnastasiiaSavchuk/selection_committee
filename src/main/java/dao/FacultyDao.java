package dao;

import domain.Faculty;
import util.AbstractCRUDOperations;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {
    /**
     * Insert the faculty's details into DB.
     *
     * @param faculty is the faculty to insert.
     */
    void createFaculty(Faculty faculty);

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
    void updateFaculty(Faculty faculty);
}
