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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ApplicationDaoImpl implements ApplicationDao {
    private static final Logger logger = Logger.getLogger(ApplicationDaoImpl.class);
    private final ApplicationCreator mapper = new ApplicationCreator();

    @Override
    public void create(Application application, List<String> locales) {
//        Application application = null;
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            connection = DBManager.getInstance().getConnectionWithDriverManager();
//            ps = connection.prepareStatement(SQLConstants.GET_APPLICATION_BY_ID);
//            ps.setInt(1, id);
//        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(connection);
//            logger.error("Cannot insert new applicant into DB: " + ex.getMessage());
//        } finally {
//            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
//            DBManager.getInstance().close(Objects.requireNonNull(ps));
//        }

    }

    @Override
    public List<Application> readAll(List<String> locales) {
        List<Application> applications = new ArrayList<>();
        Application application = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            if (locales.get(2).equals("APPLICANT")) {
                logger.info("Get the applications by user_id");
                ps = connection.prepareStatement(SQLConstants.GET_APPLICATIONS_BY_USER_ID);
                ps.setInt(1, Integer.parseInt(locales.get(1)));
                ps.setString(2, locales.get(0));
                rs = ps.executeQuery();
                while (rs.next()) {
                    application = mapper.mapRow(rs);
                }

            } else {
                logger.info("Get the applications by faculty_id");
                ps = connection.prepareStatement(SQLConstants.GET_APPLICATIONS_BY_FACULTY_ID);
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Cannot insert new applicant into DB: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
        return null;
    }

    @Override
    public Application readById(int id, List<String> locales) {
        Application application = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_APPLICATION_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                application = mapper.mapRow(rs);
            }
            Faculty faculty = new FacultyDaoImpl().readById(Objects.requireNonNull(application).getFaculty().getId(),
                    new ArrayList<>(Arrays.asList("us", "uk")));
            application.setFaculty(faculty);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Cannot read application by id from DB: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return application;
    }

    @Override
    public void update(Application application, List<String> locales) {
//        Application application = null;
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            connection = DBManager.getInstance().getConnectionWithDriverManager();
//            ps = connection.prepareStatement(SQLConstants.GET_APPLICATION_BY_ID);
//            ps.setInt(1, id);
//        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(connection);
//            logger.error("Cannot insert new applicant into DB: " + ex.getMessage());
//        } finally {
//            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
//            DBManager.getInstance().close(Objects.requireNonNull(ps));
//        }
    }

    @Override
    public void delete(int id) {
//        Application application = null;
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            connection = DBManager.getInstance().getConnectionWithDriverManager();
//            ps = connection.prepareStatement(SQLConstants.GET_APPLICATION_BY_ID);
//            ps.setInt(1, id);
//        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(connection);
//            logger.error("Cannot insert new applicant into DB: " + ex.getMessage());
//        } finally {
//            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
//            DBManager.getInstance().close(Objects.requireNonNull(ps));
//        }
    }

    private static class ApplicationCreator implements EntityCreator<Application> {

        @Override
        public Application mapRow(ResultSet rs) {
            try {
                Applicant applicant = Applicant.createApplicant(rs.getInt(SQLFields.APPLICANT_ID),
                        rs.getString(SQLFields.APPLICANT_LAST_NAME),
                        rs.getString(SQLFields.APPLICANT_FIRST_NAME));
                Faculty faculty = Faculty.createFaculty(rs.getInt(SQLFields.FACULTY_ID),
                        Arrays.asList(rs.getString(SQLFields.FACULTY)));
                return Application.createApplication(rs.getInt(SQLFields.APPLICATION_ID),
                        applicant,
                        faculty,
                        rs.getInt(SQLFields.APPLICATION_SUM_OF_GRADES),
                        ApplicationStatus.values()[rs.getInt(SQLFields.STATUS_ID)]);
            } catch (SQLException ex) {
                logger.error("Cannot read and map application from DB: " + ex.getMessage());
            }
            return null;
        }
    }
}
