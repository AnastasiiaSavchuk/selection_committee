package command.admin;

import command.Command;
import dao.impl.SubjectDaoImpl;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Get list of subject entities from the db and forward to faculty create page.
 *
 * @author A.Savchuk
 */
public class FacultyCreateChoiceCommand extends Command {
    private static final long serialVersionUID = 1254789652365658541L;
    private static final Logger logger = Logger.getLogger(FacultyCreateChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("FacultyCreateChoiceCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("subjectList", subjectList);
        logger.info("Set the session attribute:subjectList --> " + subjectList);

        logger.info("FacultyCreateChoiceCommand finished");
        return Path.FACULTY_CREATE;
    }
}
