package command.outOfControl;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.ApplicationDaoImpl;
import dao.impl.GradeDaoImpl;
import domain.Applicant;
import domain.Application;
import domain.Grade;
import domain.enums.Role;
import org.apache.log4j.Logger;
import util.Helper;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Get email and password from the request, compare password (SH-256) with available hash at db and check role.
 * Forward to different pages depending from user role.
 *
 * @author A.Savchuk
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = 7845122698854785412L;
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SingInCommand started");
        String errorMessage;
        String forward = Path.ERROR;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            errorMessage = "Something went wrong! Email and password cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            //password = Helper.getPasswordHash(password);
            Applicant applicantByEmail = new ApplicantDaoImpl().readByEmail(email);

            if (Objects.isNull(applicantByEmail)) {
                errorMessage = "The email you entered isn’t connected to an account. Please try again!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return forward;
            } else {
                if (!password.equals((applicantByEmail.getPassword()))) {
                    errorMessage = "Incorrect password. Please try again!";
                    request.setAttribute("errorMessage", errorMessage);
                    logger.error("errorMessage --> " + errorMessage);
                    return forward;
                } else {
                    Role applicantRole = applicantByEmail.getRole();

                    if (applicantRole == Role.ADMIN) {
                        forward = Path.ADMIN;
                        List<Applicant> applicantList = new ApplicantDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
                        applicantList.sort(Applicant.COMPARE_BY_ID);
                        session.setAttribute("applicantList", applicantList);
                        logger.info("Set the session attribute for admin page:applicant --> " + applicantList);
                    } else {
                        forward = Path.APPLICANT;

                        List<Grade> gradeList = new GradeDaoImpl().readGradesByApplicantId(applicantByEmail.getId(), Collections.singletonList(language == null ? localeLang : language));
                        session.setAttribute("gradeList", gradeList);
                        logger.info("Set the session attribute:gradeList --> " + gradeList);

                        byte[] getCertificate = new ApplicantDaoImpl().getCertificate(applicantByEmail.getId());
                        if (getCertificate.length > 0) {
                            String certImage = Base64.getEncoder().encodeToString(getCertificate);
                            session.setAttribute("certImage", certImage);
                            logger.info("Set the session attribute:certImage --> " + certImage);
                        }

                        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicantByEmail.getId(), Collections.singletonList(language == null ? localeLang : language));
                        session.setAttribute("applicationList", applicationList);
                        logger.info("Set the session attribute  to applicant page:applicationList --> " + applicationList);
                    }

                    Applicant applicant = new ApplicantDaoImpl().readById(applicantByEmail.getId(), Collections.singletonList(language == null ? localeLang : language));
                    session.setAttribute("applicant", applicant);
                    logger.info("Set the session attribute:applicant --> " + applicant);

                    session.setAttribute("applicantRole", applicantRole);
                    logger.info("Set the session attribute:applicantRole --> " + applicantRole);
                }
            }
        }
        logger.info("LoginCommand finished");
        return forward;
    }
}
