package command.admin;

import command.Command;
import dao.impl.FacultyDaoImpl;
import domain.Faculty;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Get change parameters of faculty from request and update the faculty in the db
 *
 * @author A.Savchuk
 */
public class FacultyUpdateCommand extends Command {
    private static final long serialVersionUID = 2136547589624576981L;
    private static final Logger logger = Logger.getLogger(FacultyUpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultyUpdateCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyIdToUpdate = request.getParameter("facultyIdToUpdate");

        if (facultyIdToUpdate != null) {
            Faculty faculty = new FacultyDaoImpl().readFacultyToUpdate(Integer.parseInt(facultyIdToUpdate));
            session.setAttribute("faculty", faculty);
            logger.info("Set the session attribute: faculty --> " + faculty);
            return Path.FACULTY_UPDATE;
        }

        String id = request.getParameter("id");
        String budgetQty = request.getParameter("budgetQty");
        String totalQty = request.getParameter("totalQty");
        String englishName = request.getParameter("englishName");
        String ukrainianName = request.getParameter("ukrainianName");

        if (id == null && budgetQty == null && totalQty == null && englishName == null && ukrainianName == null) {
            errorMessage = "Required fields cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        Faculty updated = new Faculty();
        updated.setId(Integer.parseInt(id));
        updated.setBudgetQty(Integer.parseInt(budgetQty));
        updated.setTotalQty(Integer.parseInt(totalQty));
        updated.setFacultyList(Arrays.asList(englishName, ukrainianName));

        boolean isUpdated = new FacultyDaoImpl().update(updated);
        if (!isUpdated) {
            errorMessage = "Cannot update faculty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);
        logger.info("Set the session attribute: facultyList --> " + facultyList);

        logger.info("UpdateFacultyCommand finished");
        return Path.FACULTIES;
    }
}