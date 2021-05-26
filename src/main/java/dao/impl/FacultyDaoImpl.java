package dao.impl;

import dao.FacultyDao;
import domain.Faculty;
import org.apache.log4j.Logger;
import sql.SQLFields;
import util.EntityCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class FacultyDaoImpl implements FacultyDao {
    private static final Logger logger = Logger.getLogger(FacultyDaoImpl.class);

    @Override
    public void create(Faculty faculty, List<String> locales) {

    }

    @Override
    public void createFacultyTranslation(Faculty faculty, List<String> locales) {

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
