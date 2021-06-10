package command.outOfControl;

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
 * Get list of faculties from db.
 *
 * @author A.Savchuk
 */
public class FacultiesCommand extends Command {
    private static final long serialVersionUID = 5401213543218796456L;
    private static final Logger logger = Logger.getLogger(FacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultiesCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);
        logger.info("Set the session attribute:facultyList --> " + facultyList);

        logger.info("FacultiesCommand finished");
        return Path.FACULTIES;
    }
}