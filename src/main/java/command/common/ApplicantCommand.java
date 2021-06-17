package command.common;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.ApplicationDaoImpl;
import dao.impl.GradeDaoImpl;
import domain.Applicant;
import domain.Application;
import domain.Grade;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Get list of application from db and show applicant page.
 *
 * @author A.Savchuk
 */
public class ApplicantCommand extends Command {
    private static final long serialVersionUID = 1654654054515346846L;
    private static final Logger logger = Logger.getLogger(ApplicantCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");

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

        logger.info("ApplicantCommand finished");
        return Path.APPLICANT;
    }
}
