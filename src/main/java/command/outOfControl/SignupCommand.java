package command.outOfControl;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import domain.enums.Role;
import org.apache.log4j.Logger;
import util.Helper;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Get email and password from the applicant for first registration. Registration processed in 2 steps.
 * Forwarded to second part for finished registration.
 *
 * @author A.Savchuk
 */
public class SignupCommand extends Command {
    private static final long serialVersionUID = 1236589741254785411L;
    private static final Logger logger = Logger.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SingUpCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (email == null || password == null || confirmPassword == null) {
            errorMessage = "Email, password and password for confirmation cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (password.length() < 8) {
            errorMessage = "The password must be at least 8 characters long!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (!password.equals(confirmPassword)) {
            errorMessage = "The password for confirmation must match the password!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
            //password = Helper.getPasswordHash(password);
            if (!Objects.isNull(new ApplicantDaoImpl().readByEmail(email))) {
                errorMessage = "Such applicant with email is already exists!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

            Applicant applicant = new ApplicantDaoImpl().loginApplicant(email, password);
            session.setAttribute("applicant", applicant);
            logger.info("Set the session attribute:applicant --> " + applicant);

            Role applicantRole = applicant.getRole();
            session.setAttribute("applicantRole", applicantRole);
            logger.info("Set the session attribute:applicantRole --> " + applicantRole);

            logger.info("SingUpCommand finished");
            return Path.SIGNUP_DETAILS;
        }
    }
}
