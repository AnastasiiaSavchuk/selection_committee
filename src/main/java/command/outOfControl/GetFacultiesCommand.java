package command.outOfControl;

import command.Command;
import dao.impl.FacultyDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.Faculty;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Retrieve list of faculties from db.
 *
 * @author A.Savchuk
 */
public class GetFacultiesCommand extends Command {
    private static final long serialVersionUID = 5401213543218796456L;
    private static final Logger logger = Logger.getLogger(GetFacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GetFacultiesCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);
        logger.info("Set the session attribute:facultyList --> " + facultyList);

        logger.info("GetFacultiesCommand finished");
        return Path.FACULTIES;
    }
}