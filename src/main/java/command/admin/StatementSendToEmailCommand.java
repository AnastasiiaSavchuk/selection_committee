package command.admin;

import command.Command;
import command.outOfControl.FacultyByIdCommand;
import dao.impl.ApplicationDaoImpl;
import dao.impl.FacultyDaoImpl;
import dao.impl.StatementDaoImpl;
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
import java.util.Objects;

/**
 * Send mail to each applicant after closing statement.
 *
 * @author A.Savchuk
 */
public class StatementSendToEmailCommand extends Command {
    private static final long serialVersionUID = 6985321478451263535L;
    private static final Logger logger = Logger.getLogger(StatementSendToEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("StatementCloseCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Faculty facultyFromSession = (Faculty) session.getAttribute("faculty");

        List<Application> applicationsToSend = new ApplicationDaoImpl().readApplicationByFacultyId(facultyFromSession.getId(), Collections.singletonList(language == null ? localeLang : language));

        new StatementDaoImpl().sendToEmail(applicationsToSend);

        Faculty faculty = new FacultyDaoImpl().readById(facultyFromSession.getId(), Collections.singletonList(language == null ? localeLang : language));
        List<Subject> subjectList = new SubjectDaoImpl().readSubjectsByFacultyId(faculty.getId(), Collections.singletonList(language == null ? localeLang : language));
        faculty.setSubjectList(subjectList);
        session.setAttribute("subjectList", subjectList);
        session.setAttribute("faculty", faculty);
        logger.info("Set the session attribute:faculty --> " + faculty);

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(faculty.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        boolean isExist = new StatementDaoImpl().isExist(applicationList);
        session.setAttribute("isExist", isExist);
        logger.info("Set the session attribute:isExist --> " + isExist);

        boolean isSent = new StatementDaoImpl().isSent(applicationList);
        session.setAttribute("isSent", isSent);
        logger.info("Set the session attribute:isSent --> " + isSent);

        logger.info("StatementCloseCommand finished");
        return Path.FACULTY_BY_ID;
    }
}
