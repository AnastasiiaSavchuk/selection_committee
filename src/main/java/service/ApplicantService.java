package service;

import dao.ApplicantDao;
import domain.Applicant;
import util.AbstractCRUDOperations;

public interface ApplicantService extends AbstractCRUDOperations<Applicant>, ApplicantDao {
}
