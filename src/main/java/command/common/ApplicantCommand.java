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
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Retrieve list of application from db and show applicant page.
 *
 * @author A.Savchuk
 */
public class ApplicantCommand extends Command {
    private static final long serialVersionUID = 4596874123547896542L;
    private static final Logger logger = Logger.getLogger(ApplicantCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);

        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        byte[] getCertificate = new ApplicantDaoImpl().getCertificate(applicant.getId());
        if (getCertificate.length > 0) {
            String certificate = Base64.getEncoder().encodeToString(getCertificate);
            session.setAttribute("Set the session attribute:certificate -->", certificate);
        }

        logger.info("ApplicantCommand finished");
        return Path.APPLICANT;
    }
}
