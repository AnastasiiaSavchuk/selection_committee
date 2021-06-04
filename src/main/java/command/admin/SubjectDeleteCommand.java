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
    private static final long serialVersionUID = 461088540440463073L;
    private static final Logger logger = Logger.getLogger(SubjectDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectDeleteCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String subjectIdToDelete = request.getParameter("subjectIdToDelete");

        if (subjectIdToDelete.isEmpty()) {
            errorMessage = "Something went wrong! SubjectId cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isDeleted = new SubjectDaoImpl().delete(Integer.parseInt(subjectIdToDelete));
        if (!isDeleted) {
            errorMessage = "Something went wrong! Unable to delete subject!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        subjectList.sort(Subject.COMPARE_BY_ID);
        session.setAttribute("subjectList", subjectList);
        logger.info("Set the session attribute:facultyList --> " + subjectList);

        logger.debug("SubjectDeleteCommand finished");
        return Path.SUBJECTS;
    }
}