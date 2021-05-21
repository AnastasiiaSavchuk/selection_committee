package dao;

import domain.Applicant;
import util.AbstractCRUDOperations;

public interface ApplicantDao extends AbstractCRUDOperations<Applicant> {

    /**
     * Insert an applicant into user table.
     *
     * @param email    as login of the applicant
     * @param password applicant password
     */
    void loginApplicant(String email, String password);

    /**
     * Returns a user with the given login.
     *
     * @param login is an applicant login.
     * @return an applicant entity.
     */
    Applicant readByLogin(String login);

    /**
     * Updates the applicant's blocked status in the DB.
     *
     * @param id        is an applicant's id.
     * @param isBlocked is a blocked status.
     */
    void updateByAdmin(int id, boolean isBlocked);
}
