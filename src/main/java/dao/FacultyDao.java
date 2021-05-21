package dao;

import domain.Subject;
import util.AbstractCRUDOperations;
import domain.Faculty;

import java.util.List;

public interface FacultyDao extends AbstractCRUDOperations<Faculty> {
    /**
     * Insert the subject's details into DB.
     *
     * @param subject is the subject to insert.
     * @param locales is the locales data.
     */
    void createFacultyTranslation(Faculty faculty, List<String> locales);

    /**
     * Get the current list of all subjects account's details from DB by faculty id.
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
    void updateSubjectPassingGrade(Subject subject);
}
