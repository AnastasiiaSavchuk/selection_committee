package service;

import dao.GradleDao;
import domain.Grade;
import util.AbstractCRUDOperations;

public interface GradeService extends AbstractCRUDOperations<Grade>, GradleDao {
}
