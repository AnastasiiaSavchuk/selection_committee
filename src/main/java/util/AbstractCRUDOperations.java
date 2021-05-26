package util;

import java.util.List;

/**
 * Abstract CRUD operations for entities with DB.
 *
 * @param <E> is a type of entity.
 * @author A.Savchuk.
 */
public interface AbstractCRUDOperations<E> {

    /**
     * Insert the single entity into DB.
     *
     * @param e       is the object of type entity to insert.
     */
    void create(E e);

    /**
     * Get the current list of all entities accounts details from DB.
     *
     * @param locales is the locales data.
     * @return the list of objects type.
     */
    List<E> readAll(List<String> locales);

    /**
     * Get the entity details by id from DB.
     *
     * @param id      is the entity details id.
     * @return the single entity.
     */
    E readById(int id);

    /**
     * Updates the entity's details in the DB.
     *
     * @param e       is the entity to update.
     */
    public void update(E e);

    /**
     * Deletes the entity based on its identifier.
     *
     * @param id is the entity details id.
     */
    void delete(int id);
}