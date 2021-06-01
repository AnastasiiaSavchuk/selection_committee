package command.admin;

import command.Command;
import dao.impl.FacultyDaoImpl;
import domain.Faculty;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Get fields to create a new faculty from facultyCreate page and insert into db.
 *
 * @author A.Savchuk.
 */
public class FacultyCreateCommand extends Command {
    private static final long serialVersionUID = 5874123654789123523L;
    private static final Logger logger = Logger.getLogger(FacultyCreateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultyCreateCommand started");
        String errorMessage;

        String englishName = request.getParameter("englishName");
        String ukrainianName = request.getParameter("ukrainianName");
        String budgetQty = request.getParameter("budgetQty");
        String totalQty = request.getParameter("totalQty");
        String[] subjectIdList = request.getParameterValues("subject");

        if (englishName.isEmpty() || ukrainianName.isEmpty() || budgetQty.isEmpty()
                || totalQty.isEmpty() || subjectIdList.length == 0) {
            errorMessage = "Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
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

            boolean isInsert = new FacultyDaoImpl().create(newFaculty);
            if (!isInsert) {
                errorMessage = "Required fields cannot be empty!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }
            request.setAttribute("faculty", newFaculty);
            logger.info("Faculty --> " + newFaculty);
            logger.info("FacultyCreateCommand finished");
            return Path.FACULTY;
        }
    }
}
