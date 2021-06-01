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
    private static final DBManager DB_MANAGER = DBManager.getInstance();
    private static final FacultyCreator CREATOR = new FacultyCreator();

    @Override
    public boolean create(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY);
            ps.setInt(1, faculty.getBudgetQty());
            ps.setInt(2, faculty.getTotalQty());
            ps.executeUpdate();
            logger.info("Inserted faculty");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert faculty: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createFacultyTranslation(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_TRANSLATION);
            ps.setString(1, faculty.getFacultyList().get(0));
            ps.setString(2, faculty.getFacultyList().get(1));
            ps.executeUpdate();
            logger.info("Inserted faculty's translation");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert faculty's details: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createFacultySubject(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            for (Subject subject : faculty.getSubjectList()) {
                ps = connection.prepareStatement(SQLConstants.INSERT_FACULTY_SUBJECT);
                ps.setInt(1, faculty.getId());
                ps.setInt(2, subject.getId());
                ps.executeUpdate();
            }
            logger.info("Inserted subjects to faculty");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert subjects to faculty: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
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
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_FACULTIES);
            ps.setString(1, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                facultyList.add(CREATOR.mapRow(rs));
            }
            for (Faculty faculty : facultyList) {
                faculty.setSubjectList(new SubjectDaoImpl().readSubjectsByFacultyId(faculty.getId(), locales));
            }
            logger.info("Received list of faculties");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of faculties: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return facultyList;
    }

    @Override
    public Faculty readById(int id, List<String> locales) {
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_FACULTY_BY_ID);
            ps.setInt(1, id);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            if (rs.next()) {
                faculty = CREATOR.mapRow(rs);
            }

            List<Subject> subjectList = new SubjectDaoImpl().readSubjectsByFacultyId(id, locales);
            Objects.requireNonNull(faculty).getSubjectList().addAll(subjectList);
            logger.info("Received faculty by id: " + id);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get faculty by id: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return faculty;
    }

    @Override
    public void update(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_FACULTY);
            ps.setInt(1, faculty.getBudgetQty());
            ps.setInt(2, faculty.getTotalQty());
            ps.setInt(3, faculty.getId());
            ps.executeUpdate();
            logger.info("Updated faculty");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update faculty: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void updateFacultyTranslation(Faculty faculty) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
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
                    default:
                        break;
                }
                ps.executeUpdate();
            }
            logger.info("Updated faculty's translation");
        } catch (
                SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update faculty's details: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_FACULTY);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted faculty by id: " + id);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to delete faculty: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    private static class FacultyCreator implements EntityCreator<Faculty> {

        @Override
        public Faculty mapRow(ResultSet rs) {
            Faculty faculty = new Faculty();
            try {
                faculty.setId(rs.getInt(SQLFields.FACULTY_ID));
                faculty.setBudgetQty(rs.getInt(SQLFields.FACULTY_BUDGET_QTY));
                faculty.setTotalQty(rs.getInt(SQLFields.FACULTY_TOTAL_QTY));
                faculty.setFacultyList(Arrays.asList(rs.getString(SQLFields.FACULTY).split("/")));
            } catch (SQLException ex) {
                logger.error("Couldn't read and map the faculty from DB: " + ex.getMessage());
            }
            return faculty;
        }
    }
}
