package dao;

import domain.Subject;
import util.AbstractCRUDOperations;

import java.util.List;

public interface SubjectDao extends AbstractCRUDOperations<Subject> {
    /**
     * Insert the subject's details into DB.
     *
     * @param subject is the subject to insert.
     */
    void createSubjectTranslation(Subject subject,List<String> locales);

    /**
     * Get the current list of all subject's details from DB by faculty id.
     *
     * @param locales is the locales data.
     * @returnList the list of subjects type.
     */
    List<Subject> getSubjectsByFacultyId(int facultyId, List<String> locales);

    /**
     * Updates the subject's details in the DB.
     *
     * @param subject is the subject to update.
     */
    void updateSubjectTranslation(Subject subject, List<String> locales);
}
