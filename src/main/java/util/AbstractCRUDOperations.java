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
     * @param locales is the locales data.
     */
    void create(E e, List<String> locales);

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
     * @param locales is the locales data.
     * @return the single entity.
     */
    E readById(int id, List<String> locales);

    /**
     * Updates the entity's details in the DB.
     *
     * @param e       is the entity to update.
     * @param locales is the locales data.
     */
    public void update(E e, List<String> locales);

    /**
     * Deletes the entity based on its identifier.
     *
     * @param id is the entity details id.
     */
    void delete(int id);
}