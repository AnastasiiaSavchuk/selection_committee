package dao.impl;

import dao.StatementDao;
import domain.Application;
import domain.Faculty;
import domain.enums.ApplicationStatus;
import org.apache.log4j.Logger;
import util.mail.MessageCreator;

import java.util.ArrayList;
import java.util.List;


public class StatementDaoImpl implements StatementDao {
    private static final Logger logger = Logger.getLogger(StatementDaoImpl.class);

    @Override
    public void changeApplicationStatus(List<Application> applicationList, Faculty faculty) {
        int places = 1;

        for (Application application : applicationList) {
            if (application.getApplicant().isBlocked()) {
                continue;
            }

            if (places <= faculty.getBudgetQty() &&
                    application.getAverageGrade() >= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= faculty.getBudgetQty() &&
                    application.getAverageGrade() <= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.BUDGET_APPROVED);
            } else if (places <= faculty.getTotalQty() &&
                    application.getAverageGrade() >= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else if (places <= faculty.getTotalQty() &&
                    application.getAverageGrade() <= faculty.getAveragePassingGrade()) {
                places++;
                application.setApplicationStatus(ApplicationStatus.CONTRACT_APPROVED);
            } else {
                application.setApplicationStatus(ApplicationStatus.REJECTED);
            }
        }
    }

    @Override
    public void rollbackApplicationStatusToInitial(List<Application> applicationList) {
        for (Application currentApp : applicationList) {
            if (currentApp.getApplicationStatus() == ApplicationStatus.BLOCKED) {
                continue;
            }
            currentApp.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        }
    }

    @Override
    public boolean updateApplicationStatusByQTY(List<Application> applicationList) {
        List<Thread> threads = new ArrayList<>();
        for (Application application : applicationList) {
            Thread newThread = new Thread(() -> {
                new ApplicationDaoImpl().update(application);
            });
            newThread.start();
            threads.add(newThread);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.error("Cannot close thread: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isExist(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (application.getApplicationStatus() == ApplicationStatus.IN_PROCESSING) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sendToEmail(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (application.getApplicationStatus() == ApplicationStatus.BUDGET_APPROVED ||
                    application.getApplicationStatus() == ApplicationStatus.CONTRACT_APPROVED) {
                MessageCreator.writeSuccessfulEnrollment(application.getApplicant().getEmail(),
                        application.getApplicant().getFirstName(), application.getApplicant().getLastName(),
                        application.getApplicant().getMiddleName(), application.getApplicationStatus(),
                        application.getFaculty().getFacultyList().get(0));
            } else {
                MessageCreator.writeUnSuccessfullyEnrollment(application.getApplicant().getEmail(),
                        application.getApplicant().getFirstName(), application.getApplicant().getLastName(),
                        application.getApplicant().getMiddleName(), application.getFaculty().getFacultyList().get(0));
            }
            new ApplicationDaoImpl().updateSendEmail(application.getId(), true);
        }
    }

    @Override
    public boolean isSent(List<Application> applicationsList) {
        for (Application application : applicationsList) {
            if (!application.isSent()) {
                return false;
            }
        }
        return true;
    }
}

