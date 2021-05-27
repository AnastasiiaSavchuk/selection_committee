package command.outOfControl;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.FacultyDaoImpl;
import domain.Applicant;
import domain.Faculty;
import domain.enums.Role;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Login command. Get email and password from the request,
 * compare password (SH-256) with available hash at db and check role.
 * Forward to different pages depending from user role.
 *
 * @author A.Savchuk
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = 802733277637426263L;
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SingInCommand starts");
        String errorMessage;
        String forward = Path.ERROR;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("elanguage");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            errorMessage = "Email and password cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

//        password = Helper.getPasswordHash(password);
        Applicant applicantByEmail = new ApplicantDaoImpl().readByEmail(email);

        if (Objects.isNull(applicantByEmail)) {
            errorMessage = "The email you entered isn’t connected to an account. Please try again!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        if (!password.equals((applicantByEmail.getPassword()))) {
            errorMessage = "Incorrect password. Please try again!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        Role applicantRole = applicantByEmail.getRole();

        if (applicantRole == Role.ADMIN) {
            forward = Path.ADMIN;

            List<Faculty> facultyList = new FacultyDaoImpl().readAll(Arrays.asList(language));
            logger.info("FacultiesList to admin dash size --> " + facultyList.size());
            session.setAttribute("facultyList --> ", facultyList);
            return forward;
        }

        forward = Path.APPLICANT;

        Applicant applicant = new ApplicantDaoImpl().readById(applicantByEmail.getId(), Arrays.asList(language));
        session.setAttribute("applicant", applicant);
        logger.trace("Set the session attribute --> " + applicant);

        session.setAttribute("applicantRole", applicantRole);
        logger.trace("Set the session attribute: applicantRole --> " + applicantRole);

        logger.info("LoginCommand finished");
        return forward;
    }
}
