package command.admin;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Delete applicant from db.
 *
 * @author A.Savchuk
 */
public class ApplicantDeleteCommand extends Command {
    private static final long serialVersionUID = 461088540440463073L;
    private static final Logger logger = Logger.getLogger(ApplicantDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantDeleteCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String applicantIdToDelete = request.getParameter("applicantIdToDelete");

        if (applicantIdToDelete.isEmpty()) {
            errorMessage = "ApplicantId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isDeleted = new ApplicantDaoImpl().delete(Integer.parseInt(applicantIdToDelete));
        if (!isDeleted) {
            errorMessage = "Something went wrong! Unable to delete applicant!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Applicant> applicantList = new ApplicantDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        applicantList.sort(Applicant.COMPARE_BY_ID);
        session.setAttribute("applicantList", applicantList);
        logger.info("Set the session attribute to admin page:applicantList --> " + applicantList);

        logger.debug("ApplicantDeleteCommand finished");
        return Path.ADMIN;
    }
}
