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
    private static final long serialVersionUID = 461088540440463073L;
    private static final Logger log = Logger.getLogger(FacultyDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("FacultyDeleteCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyIdToDelete = request.getParameter("facultyIdToDelete");

        if (facultyIdToDelete.isEmpty()) {
            errorMessage = "FacultyId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }
        boolean isDeleted = new FacultyDaoImpl().delete(Integer.parseInt(facultyIdToDelete));

        if (!isDeleted) {
            errorMessage = "Failed to delete faculty!";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);
        log.info("FacultyList --> " + facultyList);

        log.debug("FacultyDeleteCommand finished");
        return Path.FACULTIES;
    }
}