package command.admin;

import command.Command;
import command.common.ApplicantCommand;
import dao.impl.ApplicantDaoImpl;
import dao.impl.ApplicationDaoImpl;
import dao.impl.GradeDaoImpl;
import domain.Applicant;
import domain.Application;
import domain.Grade;
import domain.enums.ApplicationStatus;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Get applicant's status from request and update it by admin.
 *
 * @author A.Savchuk
 */
public class ApplicantUpdateByAdminCommand extends Command {
    private static final long serialVersionUID = 1236563212545478965L;
    private static final Logger logger = Logger.getLogger(ApplicantUpdateByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantUpdateByAdminCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        Applicant applicantFromSession = (Applicant) session.getAttribute("applicant");
        String blocked = request.getParameter("blocked");

        boolean isUpdated = new ApplicantDaoImpl().updateByAdmin(applicantFromSession.getId(), Integer.parseInt(blocked) == 1);
        if (!isUpdated) {
            errorMessage = "Unable to update applicant blocked status!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Application> applicationsToChanges = new ApplicationDaoImpl().readApplicationsByUserId(applicantFromSession.getId(), Collections.singletonList(language == null ? localeLang : language));
        if (Integer.parseInt(blocked) == 1) {
            for (Application application : applicationsToChanges) {
                application.setApplicationStatus(ApplicationStatus.BLOCKED);
                new ApplicationDaoImpl().update(application);
            }
        } else {
            for (Application application : applicationsToChanges) {
                application.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
                new ApplicationDaoImpl().update(application);
            }
        }

        Applicant applicant = new ApplicantDaoImpl().readById(applicantFromSession.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicant", applicant);
        logger.info("Set the session attribute:applicant --> " + applicant);

        List<Grade> gradeList = new GradeDaoImpl().readGradesByApplicantId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("gradeList", gradeList);
        logger.info("Set the session attribute:gradeList --> " + gradeList);

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        byte[] getCertificate = new ApplicantDaoImpl().getCertificate(applicant.getId());
        if (getCertificate.length > 0) {
            String certImage = Base64.getEncoder().encodeToString(getCertificate);
            session.setAttribute("certImage", certImage);
            logger.info("Set the session attribute:certImage --> " + certImage);
        }

        logger.info("ApplicantUpdateByAdminCommand finished");
        return Path.APPLICANT;
    }
}