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
    private static final DBManager DB_MANAGER = DBManager.getInstance();
    private static final ApplicantCreator CREATOR = new ApplicantCreator();

    @Override
    public Applicant loginApplicant(String email, String password) {
        Applicant applicant = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_APPLICANT_USER_FIELDS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, password);
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        applicant = new Applicant();
                        applicant.setId(generatedKeys.getInt(1));
                        applicant.setEmail(email);
                        applicant.setRole(Role.USER);
                        logger.info("Inserted applicant with login: " + email);
                    }
                }
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert applicant: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
        return applicant;
    }

    @Override
    public boolean create(Applicant applicant) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_APPLICANT_FULL_FIELDS, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, applicant.getId());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getMiddleName());
            ps.setString(4, applicant.getLastName());
            ps.setString(5, applicant.getCity());
            ps.setString(6, applicant.getRegion());
            ps.setString(7, applicant.getSchoolName());
            ps.setBytes(8, applicant.getCertificate());
            ps.setInt(9, applicant.isBlocked() ? 1 : 0);
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        applicant.setId(generatedKeys.getInt(1));
                    }
                }
            }
            logger.info("Inserted applicant's details with id: " + applicant.getId());
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert applicant's details: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    public boolean createCertificate(Applicant applicant, byte[] certificate) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_CERTIFICATE);
            ps.setBytes(1, applicant.getCertificate());
            ps.setInt(2, applicant.getId());
            ps.executeUpdate();
            logger.info("Inserted applicant's certificate");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert applicant's certificate: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public List<Applicant> readAll(List<String> locales) {
        List<Applicant> applicantList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_APPLICANT);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicantList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of applicants");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of applicants: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return applicantList;
    }

    @Override
    public Applicant readById(int id, List<String> locales) {
        Applicant applicant = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICANT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicant = CREATOR.mapRow(rs);
            }
            logger.info("Received applicant by id: " + id);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get applicant by id: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return applicant;
    }

    public byte[] getCertificate(int applicantId) {
        byte[] certificate = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_CERTIFICATE);
            ps.setInt(1, applicantId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob(SQLFields.APPLICANT_CERTIFICATE);
                int blobLength = (int) blob.length();
                certificate = blob.getBytes(1, blobLength);
            }
            logger.info("Received applicant school certificate");
            return certificate;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            ex.printStackTrace();
            logger.error("Failed to get applicant school certificate" + ex.getMessage());
            return null;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
    }

    @Override
    public Applicant readByEmail(String email) {
        Applicant applicant = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICANT_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicant = new Applicant();
                applicant.setId(rs.getInt(SQLFields.USER_ID));
                applicant.setEmail(rs.getString(SQLFields.APPLICANT_EMAIL));
                applicant.setPassword(rs.getString(SQLFields.APPLICANT_PASSWORD));
                applicant.setRole(Role.valueOf(rs.getString(SQLFields.USER_ROLE)));
            }
            logger.info("Received applicant by login: " + email);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get applicant by login: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return applicant;
    }

    @Override
    public boolean update(Applicant applicant) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_APPLICANT);
            ps.setString(1, applicant.getEmail());
            ps.setString(2, applicant.getPassword());
            ps.setString(3, applicant.getFirstName());
            ps.setString(4, applicant.getMiddleName());
            ps.setString(5, applicant.getLastName());
            ps.setString(6, applicant.getCity());
            ps.setString(7, applicant.getRegion());
            ps.setString(8, applicant.getSchoolName());
            ps.setInt(10, applicant.getId());
            ps.executeUpdate();
            logger.info("Updated applicant's details");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update applicant's details: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public boolean updateByAdmin(int id, boolean isBlocked) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_APPLICANT_BY_ADMIN);
            ps.setInt(1, isBlocked ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Updated applicant's blocked status");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update applicant's blocked status: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_APPLICANT);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted applicant by id: " + id);
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to delete applicant: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    /**
     * Received an applicant from the result set row.
     */
    private static class ApplicantCreator implements EntityCreator<Applicant> {

        @Override
        public Applicant mapRow(ResultSet rs) {
            Applicant applicant = new Applicant();
            try {
                applicant.setId(rs.getInt(SQLFields.APPLICANT_ID));
                applicant.setEmail(rs.getString(SQLFields.APPLICANT_EMAIL));
                applicant.setPassword(rs.getString(SQLFields.APPLICANT_PASSWORD));
                applicant.setRole(Role.valueOf((rs.getInt(SQLFields.APPLICANT_ROLE) == 1 ? "ADMIN" : "USER")));
                applicant.setFirstName(rs.getString(SQLFields.APPLICANT_FIRST_NAME));
                applicant.setMiddleName(rs.getString(SQLFields.APPLICANT_MIDDLE_NAME));
                applicant.setLastName(rs.getString(SQLFields.APPLICANT_LAST_NAME));
                applicant.setCity(rs.getString(SQLFields.APPLICANT_CITY));
                applicant.setRegion(rs.getString(SQLFields.APPLICANT_REGION));
                applicant.setSchoolName(rs.getString(SQLFields.APPLICANT_SCHOOL_NAME));
                applicant.setBlocked(rs.getInt(SQLFields.APPLICANT_IS_BLOCKED) != 0);
            } catch (SQLException ex) {
                logger.error("Failed to get and map applicant from DB: " + ex.getMessage());
            }
            return applicant;
        }
    }
}
