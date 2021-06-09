package command.admin;

import command.Command;
import dao.impl.FacultyDaoImpl;
import domain.Faculty;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Get fields to create a new faculty from facultyCreate page and insert into db.
 *
 * @author A.Savchuk
 */
public class FacultyCreateCommand extends Command {
    private static final long serialVersionUID = 5323658956547412147L;
    private static final Logger logger = Logger.getLogger(FacultyCreateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultyCreateCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        String englishName = request.getParameter("englishName");
        String ukrainianName = request.getParameter("ukrainianName");
        String budgetQty = request.getParameter("budgetQty");
        String totalQty = request.getParameter("totalQty");
        String[] subjectIdList = request.getParameterValues("subject");

        if (englishName.isEmpty() || ukrainianName.isEmpty() || budgetQty.isEmpty()
                || totalQty.isEmpty()) {
            errorMessage = "Something went wrong! Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (Integer.parseInt(budgetQty) >= Integer.parseInt(totalQty)) {
            errorMessage = "Budget places quantity cannot be more or the same as total places quantity!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (Objects.isNull(subjectIdList)) {
            errorMessage = "Something went wrong! Please choose subjects to faculty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }
        List<Subject> subjectList = new ArrayList<>();

        for (String id : subjectIdList) {
            Subject subject = new Subject();
            subject.setId(Integer.parseInt(id));
            subjectList.add(subject);
        }

        Faculty newFaculty = new Faculty();

        newFaculty.setFacultyList(Arrays.asList(englishName, ukrainianName));
        newFaculty.setBudgetQty(Integer.parseInt(budgetQty));
        newFaculty.setTotalQty(Integer.parseInt(totalQty));
        newFaculty.setSubjectList(subjectList);

        boolean isInserted = new FacultyDaoImpl().create(newFaculty);
        if (!isInserted) {
            errorMessage = "Something went wrong! Unable to create new faculty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        request.setAttribute("faculty", newFaculty);
        logger.info("Set the request attribute:faculty --> " + newFaculty);

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);

        logger.info("FacultyCreateCommand finished");
        return Path.FACULTIES;
    }
}
