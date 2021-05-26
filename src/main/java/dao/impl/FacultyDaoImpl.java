package dao.impl;

import dao.FacultyDao;
import domain.Faculty;
import org.apache.log4j.Logger;
import sql.DBManager;
import sql.SQLConstants;
import sql.SQLFields;
import util.EntityCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FacultyDaoImpl implements FacultyDao {
    private static final Logger logger = Logger.getLogger(FacultyDaoImpl.class);

    @Override
    public void create(Faculty faculty, List<String> locales) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_TRANSLATION);
            ps.setString(1, faculty.getFacultyList().get(0));
            ps.setString(2, faculty.getFacultyList().get(1));
            ps.executeUpdate();
            logger.info("Inserted faculty's details:" + faculty);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert faculty's details: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createFaculty(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY);
            ps.setInt(1, faculty.getBudgetQty());
            ps.setInt(2, faculty.getTotalQty());
            ps.executeUpdate();
            logger.info("Inserted faculty: " + faculty);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert faculty: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createFacultySubject(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_SUBJECT);
        } catch (SQLException ex) {
            logger.error("Failed to insert subjects to faculty: " + ex.getMessage());
        }
    }

    @Override
    public List<Faculty> readAll(List<String> locales) {
        return null;
    }

    @Override
    public Faculty readById(int id, List<String> locales) {
        return null;
    }

    @Override
    public void update(Faculty faculty, List<String> locales) {

    }

    @Override
    public void updateFaculty(Faculty faculty) {

    }

    @Override
    public void delete(int id) {

    }

    private static class FacultyCreator implements EntityCreator<Faculty> {

        @Override
        public Faculty mapRow(ResultSet rs) {
            try {
                return Faculty.createFaculty(rs.getInt(SQLFields.FACULTY_ID),
                        rs.getInt(SQLFields.FACULTY_BUDGET_QTY),
                        rs.getInt(SQLFields.FACULTY_TOTAL_QTY),
                        Arrays.asList(rs.getString(SQLFields.FACULTY).split("/")));
            } catch (SQLException ex) {
                logger.error("Couldn't read and map the faculty from DB: " + ex.getMessage());
            }
            return null;
        }
    }
}
