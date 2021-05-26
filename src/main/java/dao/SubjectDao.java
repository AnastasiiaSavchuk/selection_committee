package dao;

import domain.Subject;
import util.AbstractCRUDOperations;

import java.util.List;

public interface SubjectDao extends AbstractCRUDOperations<Subject> {
    /**
     * Insert subject's details into DB.
     *
     * @param subject entity to insert.
     */
    void createSubjectTranslation(Subject subject);

    /**
     * Get list of all subject's details by faculty id from DB.
     *
     * @param locales locales data.
     * @returnList list type of entities.
     */
    List<Subject> readSubjectsByFacultyId(int facultyId, List<String> locales);

    /**
     * Updates subject's details in the DB.
     *
     * @param subject entity to update.
     */
    void updateSubjectTranslation(Subject subject);
}
