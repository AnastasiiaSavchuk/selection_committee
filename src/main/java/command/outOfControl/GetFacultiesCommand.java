package command.outOfControl;

import command.Command;
import dao.impl.FacultyDaoImpl;
import domain.Applicant;
import domain.Faculty;
import org.apache.log4j.Logger;
import util.Helper;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Retrieve list of faculties from db and sort it according to demand params
 *
 * @author A.Savchuk.
 */
public class GetFacultiesCommand extends Command {
    private static final long serialVersionUID = -7490635096350714850L;
    private static final Logger log = Logger.getLogger(GetFacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("GetFacultiesCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        String sortedType = request.getParameter("sortedType");
        log.info("faculty sorted type -->" + sortedType);

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);

        if (sortedType == null) {
            Helper.chooseSortedType("nameAsc", facultyList);
        } else {
            Helper.chooseSortedType(sortedType, facultyList);
        }

        session.setAttribute("facultyList", facultyList);
        log.info("FacultyList --> " + facultyList);

        log.info("GetFacultiesCommand finished");
        return Path.FACULTIES;
    }
}