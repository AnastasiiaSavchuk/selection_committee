package dao.impl;

import dao.GradleDao;
import domain.Application;
import domain.Grade;
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

public class GradeDaoImpl implements GradleDao {
    private static final Logger logger = Logger.getLogger(GradeDaoImpl.class);
    private static final DBManager DB_MANAGER = DBManager.getInstance();
    private static final GradeCreator CREATOR = new GradeCreator();

    @Override
    public Grade createGrade(Grade grade) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_GRADE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, grade.getApplicant().getId());
            ps.setInt(2, grade.getSubject().getId());
            ps.setInt(3, grade.getGrade());
            if (ps.executeUpdate() > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        grade.setId(generatedKeys.getInt(1));
                    }
                }
            }
            logger.info("Inserted grade");
            return grade;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert grade: " + ex.getMessage());
            return null;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public boolean createApplicationGrade(Application application) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            for (Grade grade : application.getGradeList()) {
                ps = connection.prepareStatement(SQLConstants.INSERT_APPLICATION_GRADE);
                ps.setInt(1, application.getId());
                ps.setInt(2, grade.getId());
                ps.executeUpdate();
            }
            logger.info("Inserted grades to application");
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to insert grades to application: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    @Override
    public List<Grade> readGradesByApplicationId(int applicationId, List<String> locales) {
        List<Grade> gradeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (locales == null || locales.size() == 0)
            return gradeList;

        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.GET_GRADES_BY_APPLICATION_ID);
            ps.setInt(1, applicationId);
            ps.setString(2, locales.get(0));
            rs = ps.executeQuery();
            while (rs.next()) {
                gradeList.add(CREATOR.mapRow(rs));
            }
            logger.info("Received list of grades by applicationId");
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to get list of grades by applicationId: " + ex.getMessage());
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
            DB_MANAGER.close(Objects.requireNonNull(rs));
        }
        return gradeList;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DB_MANAGER.getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_GRADE);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted grade by id: " + id);
            return true;
        } catch (SQLException ex) {
            DB_MANAGER.rollbackAndClose(connection);
            logger.error("Failed to delete grade: " + ex.getMessage());
            return false;
        } finally {
            DB_MANAGER.commitAndClose(Objects.requireNonNull(connection));
            DB_MANAGER.close(Objects.requireNonNull(ps));
        }
    }

    /**
     * Received grade from the result set row.
     */
    private static class GradeCreator implements EntityCreator<Grade> {

        @Override
        public Grade mapRow(ResultSet rs) {
            Grade grade = new Grade();
            try {
                Subject subject = new Subject();
                subject.setId(rs.getInt(SQLFields.SUBJECT_ID));
                subject.setSubjectList(Arrays.asList(rs.getString(SQLFields.SUBJECT).split(Pattern.quote("/"))));
                subject.setPassingGrade(rs.getInt(SQLFields.SUBJECT_PASSING_GRADE));

                grade.setId(rs.getInt(SQLFields.GRADE_ID));
                grade.setSubject(subject);
                grade.setGrade(rs.getInt(SQLFields.GRADE));
            } catch (SQLException ex) {
                logger.error("Failed to get and map grade from DB: " + ex.getMessage());
            }
            return grade;
        }
    }
}
