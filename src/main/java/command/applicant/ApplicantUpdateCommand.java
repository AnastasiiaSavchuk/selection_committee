package command.applicant;

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
 * Get all details from request and update applicant in the db.
 *
 * @author A.Savchuk
 */
public class ApplicantUpdateCommand extends Command {
    private static final long serialVersionUID = 2541032598652301457L;
    private static final Logger logger = Logger.getLogger(ApplicantUpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicantUpdateCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String applicantIdToUpdate = request.getParameter("applicantIdToUpdate");

        if (applicantIdToUpdate != null) {
            Applicant applicant = new ApplicantDaoImpl().readById(Integer.parseInt(applicantIdToUpdate), Collections.singletonList(language == null ? localeLang : language));
            session.setAttribute("applicant", applicant);
            logger.info("Set the session attribute: applicant --> " + applicant);
            return Path.APPLICANT_UPDATE;
        }

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String schoolName = request.getParameter("schoolName");

        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()
                || city.isEmpty() || region.isEmpty() || schoolName.isEmpty()) {
            errorMessage = "Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (password.length() < 8) {
            errorMessage = "The password must be at least 8 characters long!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
            Applicant updated = new Applicant();
            updated.setId(Integer.parseInt(id));
            updated.setEmail(email);
            updated.setPassword(password);
            updated.setFirstName(firstName);
            updated.setMiddleName(middleName);
            updated.setLastName(lastName);
            updated.setCity(city);
            updated.setRegion(region);
            updated.setSchoolName(schoolName);

            boolean isInsert = new ApplicantDaoImpl().update(updated);
            if (!isInsert) {
                errorMessage = "Unable to update applicant!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

            session.setAttribute("applicant", updated);
            logger.info("Set the session attribute:applicant --> " + updated);

            List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(updated.getId(), Collections.singletonList(language == null ? localeLang : language));
            applicationList.sort(Application.COMPARE_BY_ID);
            session.setAttribute("applicationList", applicationList);

            logger.info("ApplicantUpdateCommand finished");
            return Path.APPLICANT;
        }
    }
}
