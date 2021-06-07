package dao.impl;

import dao.StatementDao;
import domain.Application;
import domain.Faculty;
import domain.enums.ApplicationStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementDaoImpl implements StatementDao {

    @Override
    public void changeApplicationStatus(List<Application> applicationList, Faculty faculty) {
        int places = 1;
        applicationList.sort(Collections.reverseOrder(Application.COMPARE_BY_AVERAGE_GRADE));

        for (Application application : applicationList) {
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
                e.printStackTrace();
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
}
