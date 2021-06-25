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
 * Delete subject from db.
 *
 * @author A.Savchuk
 */
public class SubjectDeleteCommand extends Command {
    private static final long serialVersionUID = 3256985655458545125L;
    private static final Logger logger = Logger.getLogger(SubjectDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectDeleteCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String subjectIdToDelete = request.getParameter("subjectIdToDelete");

        boolean isDeleted = new SubjectDaoImpl().delete(Integer.parseInt(subjectIdToDelete));
        if (!isDeleted) {
            errorMessage = "Unable to delete subject!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("subjectList", subjectList);

        logger.debug("SubjectDeleteCommand finished");
        return Path.SUBJECTS;
    }
}