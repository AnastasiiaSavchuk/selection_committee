package command.outOfControl;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.FacultyDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.Application;
import domain.Faculty;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Retrieve faculty from db by faculty id.
 *
 * @author A.Savchuk
 */
public class GetFacultyByIdCommand extends Command {
    private static final long serialVersionUID = -7490635096350714850L;
    private static final Logger logger = Logger.getLogger(GetFacultyByIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GetFacultyByIdCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyId = request.getParameter("facultyId");

        Faculty faculty = new FacultyDaoImpl().readById(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        List<Subject> subjectList = new SubjectDaoImpl().readSubjectsByFacultyId(faculty.getId(), Collections.singletonList(language == null ? localeLang : language));
        if (subjectList.size() == 0) {
            String errorMessage = "Something went wrong! Unable to find subjects!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.FACULTY_BY_id;
        }

        subjectList.sort(Subject.COMPARE_BY_ID);
        faculty.setSubjectList(subjectList);
        session.setAttribute("subjectList", subjectList);
        session.setAttribute("faculty", faculty);
        logger.info("Set the session attribute:faculty --> " + faculty);

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("GetFacultyByIdCommand finished");
        return Path.FACULTY_BY_id;
    }
}