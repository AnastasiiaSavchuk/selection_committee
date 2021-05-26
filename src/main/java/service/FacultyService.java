package service;

import dao.FacultyDao;
import domain.Faculty;
import util.AbstractCRUDOperations;

public interface FacultyService extends AbstractCRUDOperations<Faculty>, FacultyDao {
}
