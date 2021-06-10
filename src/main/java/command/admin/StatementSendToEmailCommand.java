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
        Faculty faculty = (Faculty) session.getAttribute("faculty");

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(faculty.getId(),
                Collections.singletonList(language == null ? localeLang : language));

        new StatementDaoImpl().sendToEmail(applicationList);

        boolean sentStatement = new StatementDaoImpl().isSentStatement(applicationList);
        session.setAttribute("faculty", faculty);
        session.setAttribute("applicationList", applicationList);
        session.setAttribute("sentStatement", sentStatement);
        logger.info("Set the session attribute:isSentStatement --> " + sentStatement);

        logger.info("StatementCloseCommand finished");
        return Path.FACULTY_BY_id;
    }
}
