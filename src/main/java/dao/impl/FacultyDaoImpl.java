package dao.impl;

import dao.FacultyDao;
import domain.Faculty;
import domain.Subject;
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

public class FacultyDaoImpl implements FacultyDao {
    private static final Logger logger = Logger.getLogger(FacultyDaoImpl.class);
    private static final FacultyCreator creator = new FacultyCreator();

    @Override
    public void create(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY);
            ps.setInt(1, faculty.getBudgetQty());
            ps.setInt(2, faculty.getTotalQty());
            ps.executeUpdate();
            logger.info("Inserted faculty");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert faculty: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createFacultyTranslation(Faculty faculty, List<String> locales) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_TRANSLATION);
            ps.setString(1, faculty.getFacultyList().get(0));
            ps.setString(2, faculty.getFacultyList().get(1));
            ps.executeUpdate();
            logger.info("Inserted faculty's details");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert faculty's details: " + ex.getMessage());
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
            for (Subject subject : faculty.getSubjectList()) {
                connection = DBManager.getInstance().getConnectionWithDriverManager();
                ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_SUBJECT);
                ps.setInt(1, faculty.getId());
                ps.setInt(2, subject.getId());
                ps.executeUpdate();
            }
            logger.info("Inserted subjects to faculty");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert subjects to faculty: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public List<Faculty> readAll(List<String> locales) {
        List<Faculty> facultyList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0)
            return facultyList;

        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_FACULTIES);
            ps.setString(1, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next())
                facultyList.add(creator.mapRow(rs));
            logger.info("Received list of faculties: " + facultyList);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            logger.error("Failed to get list of faculties: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return facultyList;
    }

    @Override
    public Faculty readById(int id) {
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_FACULTY_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                faculty = creator.mapRow(rs);
            }
            logger.info("Received faculty by id: " + id + ", " + faculty);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to get faculty by id: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return faculty;
    }

    @Override
    public void update(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.UPDATE_SUBJECT);
            ps.setInt(1, faculty.getBudgetQty());
            ps.setInt(2, faculty.getTotalQty());
            ps.setInt(3, faculty.getId());
            ps.executeUpdate();
            logger.info("Updated faculty");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to update faculty: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void updateFacultyTranslation(Faculty faculty, List<String> locales) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.UPDATE_FACULTY_TRANSLATION);
            for (int i = 0; i < faculty.getFacultyList().size(); i++) {
                switch (i) {
                    case 0:
                        ps.setString(1, faculty.getFacultyList().get(i));
                        ps.setInt(2, faculty.getId());
                        ps.setInt(3, 1);
                        break;
                    case 1:
                        ps.setString(1, faculty.getFacultyList().get(i));
                        ps.setInt(2, faculty.getId());
                        ps.setInt(3, 2);
                        break;
                }
                ps.executeUpdate();
            }
            ps.executeUpdate();
            logger.info("Updated faculty's details");
        } catch (
                SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to update subject's details: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
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
