package command.applicant;

import command.Command;
import dao.impl.FacultyDaoImpl;
import dao.impl.GradeDaoImpl;
import domain.Applicant;
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
 * Get list of subjects from faculty and forward to create application page.
 *
 * @author A.Savchuk
 */
public class ApplyToTheFacultyChoiceCommand extends Command {
    private static final long serialVersionUID = 3658954242456323258L;
    private static final Logger logger = Logger.getLogger(ApplyToTheFacultyChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplyToTheFacultyChoiceCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        String facultyId = request.getParameter("facultyId");

        if (facultyId == null) {
            String errorMessage = "FacultyId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        Faculty faculty = new FacultyDaoImpl().readById(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("faculty", faculty);
        logger.info("Set the session attribute:faculty --> " + faculty);

        List<Grade> gradeList = new GradeDaoImpl().readApplicantGradesByFacultyId(Integer.parseInt(facultyId), applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("gradeList", gradeList);
        logger.info("Set the session attribute:gradeList --> " + gradeList);

        logger.info("ApplyToTheFacultyChoiceCommand finished");
        return Path.APPLY_TO_THE_FACULTY;
    }
}
