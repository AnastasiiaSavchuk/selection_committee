package command.admin;

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
 * Get faculty by id from db.
 *
 * @author A.Savchuk
 */
public class ApplicantByIdCommand extends Command {
    private static final long serialVersionUID = 1254789652534569874L;
    private static final Logger logger = Logger.getLogger(ApplicantByIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantByIdCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String applicantId = request.getParameter("applicantId");

        Applicant applicant = new ApplicantDaoImpl().readById(Integer.parseInt(applicantId), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicant", applicant);
        logger.info("Set the session attribute:applicant --> " + applicant);

        List<Grade> gradeList = new GradeDaoImpl().readGradesByApplicantId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("gradeList", gradeList);
        logger.info("Set the session attribute:gradeList --> " + gradeList);

        byte[] getCertificate = new ApplicantDaoImpl().getCertificate(applicant.getId());
        if (getCertificate.length > 0) {
            String certImage = Base64.getEncoder().encodeToString(getCertificate);
            session.setAttribute("certImage", certImage);
            logger.info("Set the session attribute:certImage --> " + certImage);
        }

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("ApplicantByIdCommand finished");
        return Path.APPLICANT;
    }
}