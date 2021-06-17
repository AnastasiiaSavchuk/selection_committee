package command.outOfControl;

import command.Command;
import dao.impl.SubjectDaoImpl;
import domain.Applicant;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Forward request to signup page.
 *
 * @author A.Savchuk.
 */
public class SignupChoiceCommand extends Command {
    private static final long serialVersionUID = 1635895441254785521L;
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SignupChoiceCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));

        if (subjectList.size() == 0) {
            String errorMessage = "Unable to find subjects!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        subjectList.sort(Subject.COMPARE_BY_ID);
        session.setAttribute("subjectList", subjectList);
        logger.info("Set the session attribute:subjectList --> " + subjectList);

        logger.info("RegistrationChoiceCommand finished");
        return Path.SIGNUP;
    }
}