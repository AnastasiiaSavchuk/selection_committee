package dao;

import domain.Applicant;
import util.AbstractCRUDOperations;

public interface ApplicantDao extends AbstractCRUDOperations<Applicant> {

    /**
     * Insert applicant into user table.
     *
     * @param email    applicant's login.
     * @param password applicant's password.
     */
    void loginApplicant(String email, String password);

    /**
     * Returns user by login.
     *
     * @param login applicant's login.
     * @return single applicant.
     */
    Applicant readByLogin(String login);

    /**
     * Update applicant's blocked status in the DB.
     *
     * @param id        applicant's id.
     * @param isBlocked blocked status.
     */
    void updateByAdmin(int id, boolean isBlocked);
}
