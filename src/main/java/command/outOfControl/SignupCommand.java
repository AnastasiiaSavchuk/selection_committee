package command.outOfControl;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import domain.enums.Role;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Signup command. Get email and password from the applicant for first registration.
 * Registration processed in 2 steps.
 * Forwarded to second part for finished registration.
 *
 * @author A.Savchuk
 */
public class SignupCommand extends Command {
    private static final long serialVersionUID = -171209257031636786L;
    private static final Logger logger = Logger.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SingUpCommand starts");
        String errorMessage;
        String forward = Path.ERROR;

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            errorMessage = "Email and password cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

//        password = Helper.getPasswordHash(password);
        if (Objects.isNull(new ApplicantDaoImpl().readByEmail(email))) {
            logger.info("Signup new applicant started");
            Applicant applicant = new ApplicantDaoImpl().loginApplicant(email, password);

            session.setAttribute("applicant", applicant);
            logger.trace("Set the session attribute: applicant --> " + applicant);

            Role applicantRole = applicant.getRole();
            session.setAttribute("applicantRole", applicantRole);
            logger.trace("Set the session attribute: applicantRole --> " + applicantRole);

            logger.info(applicant + " logged as " + applicant.getEmail());
            forward = Path.SIGNUP_DETAILS;
        } else {
            errorMessage = "Such applicant with email is already exists!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        logger.info("SingUpCommand finished");
        return forward;
    }
}
