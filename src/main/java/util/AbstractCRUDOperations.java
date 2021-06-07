package util;


import domain.Applicant;
import domain.Faculty;

import java.util.List;

/**
 * Abstract CRUD operations for entities with DB.
 *
 * @param <E> type of entity.
 * @author A.Savchuk.
 */
public interface AbstractCRUDOperations<E> {

    /**
     * Insert single entity into DB.
     *
     * @param e type of entity to insert.
     */
    boolean create(E e);

    /**
     * Get the current list of all entities from DB.
     *
     * @param locales locales data.
     * @return list type of entities.
     */
    List<E> readAll(List<String> locales);

    /**
     * Get entity details by id from DB.
     *
     * @param id entity details id.
     * @return single entity.
     */
    E readById(int id, List<String> locales);

    /**
     * Update entity's details in the DB.
     *
     * @param e entity to update.
     */
    boolean update(E e);

    /**
     * Delete entity based from DB.
     *
     * @param id entity details id.
     */
    boolean delete(int id);
}