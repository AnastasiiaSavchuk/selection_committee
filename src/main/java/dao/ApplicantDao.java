package dao;

import domain.Applicant;
import util.AbstractCRUDOperations;

import java.sql.SQLException;

public interface ApplicantDao extends AbstractCRUDOperations<Applicant> {

    /**
     * Insert applicant into user table.
     *
     * @param email    applicant's login.
     * @param password applicant's password.
     * @return
     */
    Applicant loginApplicant(String email, String password);

    /**
     * Get certificate image from applicant table
     * @return true if succeed
     */
    byte[] getCertificate(int applicantId);

    /**
     * Returns user by login.
     *
     * @param email applicant's login.
     * @return single applicant.
     */
    Applicant readByEmail(String email) throws SQLException;

    /**
     * Update applicant's blocked status in the DB.
     *  @param id        applicant's id.
     * @param isBlocked blocked status.
     * @return
     */
    boolean updateByAdmin(int id, boolean isBlocked);
}
