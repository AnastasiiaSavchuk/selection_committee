package command.admin;

import command.Command;
import command.outOfControl.GetFacultyByIdCommand;
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

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(faculty.getId(),
                Collections.singletonList(language == null ? localeLang : language));

        new StatementDaoImpl().rollbackApplicationStatusToInitial(applicationList);

        boolean isCompleted = new StatementDaoImpl().updateApplicationStatusByQTY(applicationList);
        if (!isCompleted) {
            String errorMessage = "Something went wrong! Unable to roll back applications!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        session.setAttribute("faculty", faculty);
        logger.info("Set the session attribute:faculty  --> " + faculty);

        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList  --> " + applicationList);

        boolean statementExisted = new StatementDaoImpl().isExist(applicationList);
        session.setAttribute("statementExisted", statementExisted);
        logger.info("Set the session attribute:isExist --> " + statementExisted);

        logger.info("SubjectCreateCommand finished");
        return Path.FACULTY_BY_id;
    }
}
