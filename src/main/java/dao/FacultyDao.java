package dao;

import domain.Faculty;
import util.AbstractCRUDOperations;

import java.sql.Connection;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {

    /**
     * Insert subjects to the faculty in the DB.
     *
     * @param faculty entity to insert.
     */
    void createFacultySubject(Connection connection, Faculty faculty);

    /**
     * Get entity details by id for update with all of translation name from DB.
     *
     * @param id entity details id.
     * @return single entity.
     */
    Faculty readFacultyToUpdate(int id);
}
