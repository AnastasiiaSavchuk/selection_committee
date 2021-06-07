package command.outOfControl;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.FacultyDaoImpl;
import dao.impl.StatementDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.Applicant;
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
import java.util.Objects;

/**
 * Retrieve faculty from db by faculty id.
 *
 * @author A.Savchuk
 */
public class GetFacultyByIdCommand extends Command {
    private static final long serialVersionUID = 5648648604663405645L;
    private static final Logger logger = Logger.getLogger(GetFacultyByIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GetFacultyByIdCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        String facultyId = request.getParameter("facultyId");

        Faculty faculty = new FacultyDaoImpl().readById(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        List<Subject> subjectList = new SubjectDaoImpl().readSubjectsByFacultyId(faculty.getId(), Collections.singletonList(language == null ? localeLang : language));
        if (subjectList.size() == 0) {
            String errorMessage = "Something went wrong! Unable to find subjects!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
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

        if (Objects.nonNull(applicant)) {
            boolean applicantExist = new ApplicationDaoImpl().isExist(applicant, faculty);
            session.setAttribute("applicantExist", applicantExist);
            logger.info("Set the session attribute:isExist --> " + applicantExist);
        }

        boolean statementExisted = new StatementDaoImpl().isExist(applicationList);
        session.setAttribute("statementExisted", statementExisted);
        logger.info("Set the session attribute:isExist --> " + statementExisted);


        logger.info("GetFacultyByIdCommand finished");
        return Path.FACULTY_BY_id;
    }
}