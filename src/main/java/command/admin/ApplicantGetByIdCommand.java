package command.admin;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.ApplicationDaoImpl;
import domain.Applicant;
import domain.Application;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Get faculty from db by faculty id.
 *
 * @author A.Savchuk
 */
public class ApplicantGetByIdCommand extends Command {
    private static final long serialVersionUID = 1254789652534569874L;
    private static final Logger logger = Logger.getLogger(ApplicantGetByIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantGetByIdCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String applicantId = request.getParameter("applicantId");

        Applicant applicant = new ApplicantDaoImpl().readById(Integer.parseInt(applicantId), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicant", applicant);
        logger.info("Set the session attribute:applicant --> " + applicant);

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("ApplicantGetByIdCommand finished");
        return Path.APPLICANT;
    }
}