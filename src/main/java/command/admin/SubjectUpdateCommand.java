package command.admin;

import command.Command;
import dao.impl.SubjectDaoImpl;
import domain.Subject;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Get change parameters of subject from request and update the subject in the db.
 *
 * @author A.Savchuk
 */
public class SubjectUpdateCommand extends Command {
    private static final long serialVersionUID = 2365894512748951254L;
    private static final Logger logger = Logger.getLogger(SubjectUpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectUpdateCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String subjectIdToUpdate = request.getParameter("subjectIdToUpdate");

        if (subjectIdToUpdate != null) {
            Subject subject = new SubjectDaoImpl().readSubjectToUpdate(Integer.parseInt(subjectIdToUpdate));
            session.setAttribute("subject", subject);
            logger.info("Set the session attribute:subject --> " + subject);
            return Path.SUBJECT_UPDATE;
        }

        String id = request.getParameter("id");
        String englishName = request.getParameter("englishName");
        String ukrainianName = request.getParameter("ukrainianName");
        String passingGrade = request.getParameter("passingGrade");

        if (id.isEmpty() && englishName.isEmpty() && ukrainianName.isEmpty() && passingGrade.isEmpty()) {
            errorMessage = "Something went wrong! Required fields cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        Subject updated = new Subject();
        updated.setId(Integer.parseInt(id));
        updated.setPassingGrade(Integer.parseInt(passingGrade));
        updated.setSubjectList(Arrays.asList(englishName, ukrainianName));

        boolean isUpdated = new SubjectDaoImpl().update(updated);
        if (!isUpdated) {
            errorMessage = "Something went wrong! Unable to update subject!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        subjectList.sort(Subject.COMPARE_BY_ID);
        session.setAttribute("subjectList", subjectList);
        logger.info("Set the session attribute:subjectList --> " + subjectList);

        logger.info("SubjectUpdateCommand finished");
        return Path.SUBJECTS;
    }
}