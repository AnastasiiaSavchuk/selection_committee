package service;

import dao.SubjectDao;
import domain.Subject;
import util.AbstractCRUDOperations;

public interface SubjectService extends AbstractCRUDOperations<Subject>, SubjectDao {
}
