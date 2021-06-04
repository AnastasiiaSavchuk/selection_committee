package dao;

import domain.Subject;
import util.AbstractCRUDOperations;

import java.util.List;

public interface SubjectDao extends AbstractCRUDOperations<Subject> {
    /**
     * Get list of all subject's details by faculty id from DB.
     *
     * @param locales locales data.
     * @returnList list type of entities.
     */
    List<Subject> readSubjectsByFacultyId(int facultyId, List<String> locales);

    /**
     * Get subject details by id for update with all of translation name from DB.
     *
     * @param id entity details id.
     * @return single entity.
     */
    Subject readSubjectToUpdate(int id);
}
