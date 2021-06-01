package command.outOfControl;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Get faculty from db by faculty id.
 *
 * @author A.Savchuk.
 */
public class GetFacultyByIdCommand extends Command {
    private static final long serialVersionUID = -7490635096350714850L;
    private static final Logger log = Logger.getLogger(GetFacultyByIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("GetFacultiesCommand starts");
        String forward = Path.FACULTY;

//        HttpSession session = request.getSession();
//        String localeLang = request.getLocale().getLanguage();
//        String language = (String) session.getAttribute("elanguage");
//
//        String sortedType = request.getParameter("sortedType");
//        log.info("faculty sorted type -->" + sortedType);
//
//        Faculty faculty = new FacultyDaoImpl().readById(Arrays.asList(language == null ? localeLang : language));
//
//        session.setAttribute("faculty", faculty);
//        log.info("Faculty by id --> " +  faculty);

        log.info("GetFacultiesCommand finished");
        return forward;
    }
}