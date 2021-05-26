package dao.impl;

import dao.SubjectDao;
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
import java.util.regex.Pattern;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class);
    private final SubjectCreator creator = new SubjectCreator();

    @Override
    public void create(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_SUBJECT);
            ps.setInt(1, subject.getPassingGrade());
            ps.executeUpdate();
            logger.info("Inserted subject's passing grade");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert subject's passing grade: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void createSubjectTranslation(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.INSERT_SUBJECT_TRANSLATION);
            ps.setString(1, subject.getSubjectList().get(0));
            ps.setString(2, subject.getSubjectList().get(1));
            ps.executeUpdate();
            logger.info("Inserted subject's details");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to insert subject's details: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
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
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_ALL_SUBJECTS);
            ps.setString(1, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next())
                subjectList.add(creator.mapRow(rs));
            logger.info("Received list of subjects: " + subjectList);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            logger.error("Failed to get list of subjects: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return subjectList;
    }

    @Override
    public List<Subject> readSubjectsByFacultyId(int facultyId, List<String> locales) {
        List<Subject> subjectList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0)
            return subjectList;

        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_SUBJECTS_BY_FACULTY_ID);
            ps.setInt(1, facultyId);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next())
                subjectList.add(creator.mapRow(rs));
            logger.info("Received list of subjects by faculty_id: " + subjectList);
        } catch (SQLException ex) {
            logger.error("Failed to get list of subjects by faculty_id: " + ex.getMessage());
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
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
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.GET_SUBJECT_BY_ID);
            ps.setInt(1, id);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            if (rs.next()) {
                subject = creator.mapRow(rs);
            }
            logger.info("Received subject by id: " + id + ", " + subject);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to get subject by id: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
            DBManager.getInstance().close(Objects.requireNonNull(rs));
        }
        return subject;
    }

    @Override
    public void update(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.UPDATE_SUBJECT);
            ps.setInt(1, subject.getPassingGrade());
            ps.setInt(2, subject.getId());
            ps.executeUpdate();
            logger.info("Updated subject's passing grade");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to update subject's passing grade: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public void updateSubjectTranslation(Subject subject) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
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
                }
                ps.executeUpdate();
            }
            ps.executeUpdate();
            logger.info("Updated subject's details");
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
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnectionWithDriverManager();
            ps = connection.prepareStatement(SQLConstants.DELETE_SUBJECT);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted subject by id: " + id);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.error("Failed to delete subject: " + ex.getMessage());
        } finally {
            DBManager.getInstance().commitAndClose(Objects.requireNonNull(connection));
            DBManager.getInstance().close(Objects.requireNonNull(ps));
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
