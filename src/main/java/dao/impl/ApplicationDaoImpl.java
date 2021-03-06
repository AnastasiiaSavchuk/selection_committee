package dao.impl;

import dao.ApplicationDao;
import domain.Applicant;
import domain.Application;
import domain.Faculty;
import domain.enums.ApplicationStatus;
import org.apache.log4j.Logger;
import sql.DBManager;
import sql.SQLConstants;
import sql.SQLFields;
import util.EntityCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ApplicationDaoImpl implements ApplicationDao {
    private static final Logger logger = Logger.getLogger(ApplicationDaoImpl.class);
    private static final DBManager DB_MANAGER = DBManager.getInstance();
    private static final ApplicationCreator CREATOR = new ApplicationCreator();

    @Override
    public boolean create(Application application) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_APPLICATION, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, application.getApplicant().getId());
            ps.setInt(2, application.getFaculty().getId());
            ps.setInt(3, application.isSent() ? 1 : 0);
            ps.setString(4, String.valueOf(application.getApplicationStatus()));
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        application.setId(generatedKeys.getInt(1));
                    }
                }
            }
            logger.info("Inserted application");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert application: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }

    }

    @Override
    public List<Application> readAll(List<String> locales) {
        return null;
    }

    @Override
    public List<Application> readApplicationsByUserId(int userId, List<String> locales) {
        List<Application> applicationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0)
            return applicationList;

        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICATIONS_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                applicationList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of application by userId");
            return applicationList;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of application by userId: " + ex.getMessage());
            return null;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
    }

    @Override
    public List<Application> readApplicationByFacultyId(int facultyId, List<String> locales) {
        List<Application> applicationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0) {
            return applicationList;
        }

        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICATIONS_BY_FACULTY_ID);
            ps.setInt(1, facultyId);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                applicationList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of application by facultyId");
            return applicationList;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of application by facultyId: " + ex.getMessage());
            return null;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
    }

    @Override
    public Application readById(int id, List<String> locales) {
        Application application = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICATION_BY_ID);
            ps.setInt(1, id);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            if (rs.next()) {
                application = CREATOR.mapRow(rs);
            }
            logger.info("Received application by id: " + id);
            return application;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get application by id: " + ex.getMessage());
            return null;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
    }

    @Override
    public boolean update(Application application) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_APPLICATION);
            ps.setString(1, String.valueOf(application.getApplicationStatus()));
            ps.setInt(2, application.getId());
            ps.executeUpdate();
            logger.info("Updated application status");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update application status: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    public void updateSendEmail(int id, boolean isSent) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_SEND_EMAIL);
            ps.setInt(1, isSent ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Updated applicant's send status");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update applicant's send status: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    public boolean isExist(Applicant applicant, Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.IS_EXIST);
            ps.setInt(1, applicant.getId());
            ps.setInt(2, faculty.getId());
            ps.execute();

            rs = ps.getResultSet();
            if (!rs.next()) {
                logger.info("Current application is exist");
                return false;
            }
        } catch (SQLException ex) {
            logger.error("Current application is not created: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_APPLICATION);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted application by id: " + id);
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to delete application: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    private static class ApplicationCreator implements EntityCreator<Application> {

        @Override
        public Application mapRow(ResultSet rs) {
            Application application = new Application();
            try {
                Applicant applicant = new Applicant();
                applicant.setId(rs.getInt(SQLFields.APPLICANT_ID));
                applicant.setEmail(rs.getString(SQLFields.APPLICANT_EMAIL));
                applicant.setLastName(rs.getString(SQLFields.APPLICANT_LAST_NAME));
                applicant.setFirstName(rs.getString(SQLFields.APPLICANT_FIRST_NAME));
                applicant.setMiddleName(rs.getString(SQLFields.APPLICANT_MIDDLE_NAME));
                applicant.setBlocked(rs.getInt(SQLFields.APPLICANT_IS_BLOCKED) != 0);

                Faculty faculty = new Faculty();
                faculty.setId(rs.getInt(SQLFields.FACULTY_ID));
                faculty.setFacultyList(Collections.singletonList(rs.getString(SQLFields.FACULTY)));

                application.setId(rs.getInt(SQLFields.APPLICATION_ID));
                application.setApplicant(applicant);
                application.setFaculty(faculty);
                application.setSumOfGrades(rs.getInt(SQLFields.APPLICATION_SUM_OF_GRADES));
                application.setAverageGrade(rs.getInt(SQLFields.APPLICATION_AVERAGE_GRADE));
                application.setSent(rs.getInt(SQLFields.APPLICATION_SET_EMAIL) != 0);
                application.setApplicationStatus(ApplicationStatus.values()[rs.getInt(SQLFields.STATUS_ID)]);
            } catch (SQLException ex) {
                logger.error("Couldn't to get and map application from DB: " + ex.getMessage());
            }
            return application;
        }
    }
}
