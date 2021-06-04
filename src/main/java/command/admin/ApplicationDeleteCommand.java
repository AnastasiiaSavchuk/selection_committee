package command.admin;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.FacultyDaoImpl;
import domain.Faculty;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Delete faculty from db.
 *
 * @author A.Savchuk
 */
public class ApplicationDeleteCommand extends Command {
    private static final long serialVersionUID = 461088540440463073L;
    private static final Logger logger = Logger.getLogger(ApplicationDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicationDeleteCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String applicationIdToDelete = request.getParameter("applicationIdToDelete");

        if (applicationIdToDelete.isEmpty()) {
            errorMessage = "Something went wrong! ApplicationId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isDeleted = new ApplicationDaoImpl().delete(Integer.parseInt(applicationIdToDelete));
        if (!isDeleted) {
            errorMessage = "Something went wrong! Unable to delete application!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        logger.debug("ApplicationDeleteCommand finished");
        return Path.FACULTY;
    }
}