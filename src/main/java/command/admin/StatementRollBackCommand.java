package command.admin;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.StatementDaoImpl;
import domain.Application;
import domain.Faculty;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Roll back statement if list of applicants changes.
 *
 * @author A.Savchuk
 */
public class StatementRollBackCommand extends Command {
    private static final long serialVersionUID = 4789654154757215214L;
    private static final Logger logger = Logger.getLogger(StatementRollBackCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("StatementRollBackCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Faculty faculty = (Faculty) session.getAttribute("faculty");

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(faculty.getId(), Collections.singletonList(language == null ? localeLang : language));

        new StatementDaoImpl().rollbackApplicationStatusToInitial(applicationList);

        boolean isCompleted = new StatementDaoImpl().updateApplicationStatusByQTY(applicationList);
        if (!isCompleted) {
            String errorMessage = "Unable to roll back applications!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isExist = new StatementDaoImpl().isExist(applicationList);
        session.setAttribute("faculty", faculty);
        session.setAttribute("applicationList", applicationList);
        session.setAttribute("isExist", isExist);
        logger.info("Set the session attribute:isExist --> " + isExist);

        logger.info("SubjectCreateCommand finished");
        return Path.FACULTY_BY_ID;
    }
}
