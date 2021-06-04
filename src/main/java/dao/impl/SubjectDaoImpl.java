package dao.impl;

import dao.SubjectDao;
import domain.Subject;
import org.apache.log4j.Logger;
import sql.DBManager;
import sql.SQLConstants;
import sql.SQLFields;
import util.EntityCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class);
    private static final DBManager DB_MANAGER = DBManager.getInstance();
    private static final SubjectCreator CREATOR = new SubjectCreator();

    @Override
    public boolean create(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, subject.getPassingGrade());
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subject.setId(generatedKeys.getInt(1));
                    }
                }
            }
            logger.info("Inserted subject passing grade");

            ps = connection.prepareStatement(SQLConstants.INSERT_SUBJECT_TRANSLATION);
            ps.setString(1, subject.getSubjectList().get(0));
            ps.setString(2, subject.getSubjectList().get(1));
            ps.executeUpdate();
            logger.info("Inserted subject's translation");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert subject: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public List<Subject> readAll(List<String> locales) {
        List<Subject> subjectList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0)
            return subjectList;

        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_SUBJECTS);
            ps.setString(1, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                subjectList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of subjects");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of subjects: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return subjectList;
    }

    @Override
    public List<Subject> readSubjectsByFacultyId(int facultyId, List<String> locales) {
        List<Subject> subjectList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0) {
            return subjectList;
        }

        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_SUBJECTS_BY_FACULTY_ID);
            ps.setInt(1, facultyId);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                subjectList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of subjects by faculty_id: " + facultyId);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of subjects by faculty_id: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return subjectList;
    }

    @Override
    public Subject readById(int id, List<String> locales) {
        Subject subject = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_SUBJECT_BY_ID);
            ps.setInt(1, id);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            if (rs.next()) {
                subject = CREATOR.mapRow(rs);
            }
            logger.info("Received subject by id: " + id);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get subject by id: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return subject;
    }

    @Override
    public Subject readSubjectToUpdate(int id) {
        Subject subject = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_SUBJECT_TO_UPDATE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                subject = CREATOR.mapRow(rs);
            }
            logger.info("Received subject to update by id: " + id);
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get subject to update by id: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return subject;
    }

    @Override
    public boolean update(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_SUBJECT);
            ps.setInt(1, subject.getPassingGrade());
            ps.setInt(2, subject.getId());
            ps.executeUpdate();
            logger.info("Updated subject's passing grade");

            ps = connection.prepareStatement(SQLConstants.UPDATE_SUBJECT_TRANSLATION);
            for (int i = 0; i < subject.getSubjectList().size(); i++) {
                switch (i) {
                    case 0:
                        ps.setString(1, subject.getSubjectList().get(i));
                        ps.setInt(2, subject.getId());
                        ps.setInt(3, 1);
                        break;
                    case 1:
                        ps.setString(1, subject.getSubjectList().get(i));
                        ps.setInt(2, subject.getId());
                        ps.setInt(3, 2);
                        break;
                    default:
                        break;
                }
                ps.executeUpdate();
            }
            ps.executeUpdate();
            logger.info("Updated subject's translation");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to update subject's passing grade: " + ex.getMessage());
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
            ps = connection.prepareStatement(SQLConstants.DELETE_SUBJECT);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted subject by id: " + id);
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to delete subject: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    /**
     * Received an subject from the result set row.
     */
    private static class SubjectCreator implements EntityCreator<Subject> {

        @Override
        public Subject mapRow(ResultSet rs) {
            Subject subject = new Subject();
            try {
                subject.setId(rs.getInt(SQLFields.SUBJECT_ID));
                subject.setPassingGrade(rs.getInt(SQLFields.SUBJECT_PASSING_GRADE));
                subject.setSubjectList(Arrays.asList(rs.getString(SQLFields.SUBJECT).split(Pattern.quote("/"))));
            } catch (SQLException ex) {
                logger.error("Couldn't get and map the subject from DB: " + ex.getMessage());
            }
            return subject;
        }
    }
}
