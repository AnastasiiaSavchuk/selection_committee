package command.outOfControl;

import command.Command;
import dao.impl.FacultyDaoImpl;
import domain.Faculty;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Helper;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Retrieve list of faculties from db and sort it according to demand params
 */
public class GetFacultiesCommand extends Command {
    private static final long serialVersionUID = -7490635096350714850L;
    private static final Logger log = Logger.getLogger(GetFacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("GetFacultiesCommand starts");
        String forward = Path.FACULTY;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        String sortedType = request.getParameter("sortedType");
        log.info("faculty sorted type -->" + sortedType);

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Arrays.asList(language == null ? localeLang : language));

        if(sortedType == null){
            Helper.chooseSortedType("nameAsc", facultyList);
        }else {
            Helper.chooseSortedType(sortedType, facultyList);
        }
        session.setAttribute("facultyList", facultyList);
        log.info("FacultyList: --> " +  facultyList);

        log.info("GetFacultiesCommand finished");
        return forward;
    }
}