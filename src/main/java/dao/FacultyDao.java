package dao;

import domain.Faculty;
import util.AbstractCRUDOperations;

import java.sql.Connection;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {
//    /**
//     * Insert faculty's details into DB.
//     *
//     * @param faculty entity to insert.
//     */
//    void createFacultyTranslation(Faculty faculty);

    /**
     * Insert subjects to the faculty in the DB.
     *
     * @param faculty entity to insert.
     */
    void createFacultySubject(Connection connection, Faculty faculty);
//
//    /**
//     * Update faculty's details in the DB.
//     *
//     * @param faculty entity to update.
//     */
//    void updateFacultyTranslation(Faculty faculty);
}
