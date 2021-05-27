package command.user;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * SignupDetails command. Get all details from the applicant for second registration.
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 3)
public class SignupDetailsCommand extends Command {
    private static final long serialVersionUID = 8456909257031636786L;
    private static final Logger logger = Logger.getLogger(SignupDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SignupDetailsCommand starts");

        HttpSession session = request.getSession();
        Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");

        String errorMessage;
        String forward = Path.ERROR;

        int applicantId = sessionApplicant.getId();
        String email = sessionApplicant.getEmail();

        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String schoolName = request.getParameter("schoolName");

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || city.isEmpty() || region.isEmpty()
                || schoolName.isEmpty()) {
            errorMessage = "Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        Applicant applicant = new Applicant();
        applicant.setId(applicantId);
        applicant.setEmail(email);
        applicant.setFirstName(firstName);
        applicant.setMiddleName(middleName);
        applicant.setLastName(lastName);
        applicant.setCity(city);
        applicant.setRegion(region);
        applicant.setSchoolName(schoolName);
        applicant.setCertificate(new byte[]{});
        applicant.setBlocked(false);
        applicant.setApplicationList(new ArrayList<>());

        boolean isInsert = new ApplicantDaoImpl().create(applicant);

        if (!isInsert) {
            errorMessage = "Unable to insert the applicant to the system. Please enter the correct data!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        session.setAttribute("applicant", applicant);
        logger.trace("Set the session attribute: applicant --> " + applicant);
        forward = Path.APPLICANT_INSERT_APPLICATION;

        logger.info("SignupDetailsCommand finished");
        return forward;
    }
}
