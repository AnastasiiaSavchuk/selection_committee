package dao.impl;

import dao.ApplicantDao;
import domain.Applicant;
import domain.enums.Role;
import org.apache.log4j.Logger;
import sql.DBManager;
import sql.SQLConstants;
import sql.SQLFields;
import util.EntityCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicantDaoImpl implements ApplicantDao {
    private static final Logger logger = Logger.getLogger(ApplicantDaoImpl.class);
    private final ApplicantCreator creator = new ApplicantCreator();

    @Override
    public void loginApplicant(String email, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_APPLICANT_USER_FIELDS);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.execute();
            logger.info("Inserted user with login: " + email);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert new user: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void create(Applicant applicant) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_APPLICANT_FULL_FIELDS);
            ps.setInt(1, applicant.getId());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getMiddleName());
            ps.setString(4, applicant.getLastName());
            ps.setString(5, applicant.getCity());
            ps.setString(6, applicant.getRegion());
            ps.setString(7, applicant.getSchoolName());
            ps.setBytes(8, applicant.getCertificate());
            ps.setInt(9, applicant.isBlocked() ? 1 : 0);
            ps.executeUpdate();
            logger.info("Inserted applicant's details with id: " + applicant.getId());
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert applicant's details: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public List<Applicant> readAll(List<String> locales) {
        List<Applicant> applicants = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_APPLICANT);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicants.add(creator.mapRow(rs));
            }
            logger.info("Received list of applicants");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to get list of applicants: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return applicants;
    }

    @Override
    public Applicant readById(int id) {
        Applicant applicant = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICANT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicant = creator.mapRow(rs);
            }
            logger.info("Received applicant by id: " + id + ", " + applicant);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to get applicant by id: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return applicant;
    }

    @Override
    public Applicant readByLogin(String login) {
        Applicant applicant = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICANT_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicant = Applicant.createApplicant(rs.getString(SQLFields.APPLICANT_EMAIL),
                        rs.getString(SQLFields.APPLICANT_PASSWORD), Role.valueOf(rs.getString(SQLFields.USER_ROLE)));
            }
            logger.info("Received applicant by login: " + login + ", " + applicant);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to get applicant by login: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return applicant;
    }

    @Override
    public void update(Applicant applicant) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_APPLICANT);
            ps.setString(1, applicant.getEmail());
            ps.setString(2, applicant.getPassword());
            ps.setString(3, applicant.getFirstName());
            ps.setString(4, applicant.getMiddleName());
            ps.setString(5, applicant.getLastName());
            ps.setString(6, applicant.getCity());
            ps.setString(7, applicant.getRegion());
            ps.setString(8, applicant.getSchoolName());
            ps.setBytes(9, applicant.getCertificate());
            ps.setInt(10, applicant.getId());
            ps.executeUpdate();
            logger.info("Updated applicant's details: " + applicant);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to update applicant's details: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void updateByAdmin(int id, boolean isBlocked) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_APPLICANT_BY_ADMIN);
            ps.setInt(1, isBlocked ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Updated applicant's blocked status");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to update applicant's blocked status: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_APPLICANT);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted applicant by id: " + id);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to delete applicant: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    /**
     * Received an applicant from the result set row.
     */
    private static class ApplicantCreator implements EntityCreator<Applicant> {

        @Override
        public Applicant mapRow(ResultSet rs) {
            byte[] certificate = null;
            try {
                Blob blob = rs.getBlob(SQLFields.APPLICANT_CERTIFICATE);
                if (blob != null) {
                    int blobLength = (int) blob.length();
                    certificate = blob.getBytes(1, blobLength);
                }
                return Applicant.createApplicant(rs.getInt(SQLFields.APPLICANT_ID),
                        rs.getString(SQLFields.APPLICANT_EMAIL),
                        rs.getString(SQLFields.APPLICANT_PASSWORD),
                        Role.valueOf((rs.getInt(SQLFields.APPLICANT_ROLE) == 1 ? "ADMIN" : "USER")),
                        rs.getString(SQLFields.APPLICANT_FIRST_NAME),
                        rs.getString(SQLFields.APPLICANT_MIDDLE_NAME),
                        rs.getString(SQLFields.APPLICANT_LAST_NAME),
                        rs.getString(SQLFields.APPLICANT_CITY),
                        rs.getString(SQLFields.APPLICANT_REGION),
                        rs.getString(SQLFields.APPLICANT_SCHOOL_NAME),
                        certificate,
                        (rs.getInt(SQLFields.APPLICANT_IS_BLOCKED) != 0));
            } catch (SQLException ex) {
                logger.error("Failed to get and map applicant from DB: " + ex.getMessage());
            }
            return null;
        }
    }
}
