package command.applicant;

import command.Command;
import dao.impl.FacultyDaoImpl;
import dao.impl.GradeDaoImpl;
import domain.Faculty;
import domain.Grade;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Get list of subject entities from the db and forward to faculties page page and forward to create faculty page.
 *
 * @author A.Savchuk
 */
public class ApplyToTheFacultyChoiceCommand extends Command {
    private static final long serialVersionUID = 8654712323232145896L;
    private static final Logger logger = Logger.getLogger(ApplyToTheFacultyChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplyToTheFacultyChoiceCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyId = request.getParameter("facultyId");

        if (facultyId == null) {
            errorMessage = "Something went wrong! FacultyId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        Faculty faculty = new FacultyDaoImpl().readByIdForApply(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("faculty", faculty);
        logger.info("Set the session attribute:faculty --> " + faculty);

        logger.info("ApplyToTheFacultyChoiceCommand finished");
        return Path.APPLY_TO_THE_FACULTY;
    }
}
