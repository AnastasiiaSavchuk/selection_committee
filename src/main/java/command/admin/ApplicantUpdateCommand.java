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
import java.util.Objects;

/**
 * Get change parameters of faculty from request and update the faculty in the db
 *
 * @author A.Savchuk
 */
public class ApplicantUpdateCommand extends Command {
    private static final long serialVersionUID = 5478963254157895416L;
    private static final Logger logger = Logger.getLogger(ApplicantUpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantUpdateCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        Applicant applicant = (Applicant) session.getAttribute("applicant");
        String isBlocked = request.getParameter("isBlocked");
        boolean status;

        if (Integer.parseInt(isBlocked) == 1) {
            status = true;
        } else {
            status = false;
        }

        if (Objects.isNull(applicant) && isBlocked.isEmpty()) {
            errorMessage = "Cannot find current applicant or blocked status is empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isUpdated = new ApplicantDaoImpl().updateByAdmin(applicant.getId(), status);
        if (!isUpdated) {
            errorMessage = "Cannot update applicant blocked status!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Applicant> applicantList = new ApplicantDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        applicantList.sort(Applicant.COMPARE_BY_ID);
        session.setAttribute("applicantList", applicantList);
        logger.info("Set the session attribute to admin page: applicantList --> " + applicantList);

        logger.info("ApplicantUpdateCommand finished");
        return Path.ADMIN;
    }
}