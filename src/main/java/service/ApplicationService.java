package service;

import dao.ApplicationDao;
import domain.Application;
import util.AbstractCRUDOperations;

public interface ApplicationService extends AbstractCRUDOperations<Application>, ApplicationDao {
}
