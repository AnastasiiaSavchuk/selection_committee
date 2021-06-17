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
 * Get list of subjects from db
 *
 * @author A.Savchuk.
 */
public class SubjectsCommand extends Command {
    private static final long serialVersionUID = 698564123547895412L;
    private static final Logger logger = Logger.getLogger(SubjectsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectsCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        subjectList.sort(Subject.COMPARE_BY_ID);
        session.setAttribute("subjectList", subjectList);
        logger.info("Set the session attribute:subjectList --> " + subjectList);

        logger.info("SubjectsCommand finished");
        return Path.SUBJECTS;
    }
}