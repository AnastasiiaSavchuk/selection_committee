package command.admin;

import command.Command;
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
public class FacultyDeleteCommand extends Command {
    private static final long serialVersionUID = 569854785412365897L;
    private static final Logger logger = Logger.getLogger(FacultyDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultyDeleteCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyIdToDelete = request.getParameter("facultyIdToDelete");

        boolean isDeleted = new FacultyDaoImpl().delete(Integer.parseInt(facultyIdToDelete));
        if (!isDeleted) {
            errorMessage = "Unable to delete faculty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("facultyList", facultyList);

        logger.debug("FacultyDeleteCommand finished");
        return Path.FACULTIES;
    }
}