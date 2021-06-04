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
 * Get fields to create a new subject from subject create page and insert into db.
 *
 * @author A.Savchuk
 */
public class SubjectCreateCommand extends Command {
    private static final long serialVersionUID = 5874123654789123523L;
    private static final Logger logger = Logger.getLogger(SubjectCreateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectCreateCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        String englishName = request.getParameter("englishName");
        String ukrainianName = request.getParameter("ukrainianName");
        String passingGrade = request.getParameter("passingGrade");

        if (englishName.isEmpty() || ukrainianName.isEmpty() || passingGrade.isEmpty()) {
            errorMessage = "Something went wrong! Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
            Subject newSubject = new Subject();
            newSubject.setPassingGrade(Integer.parseInt(passingGrade));
            newSubject.setSubjectList(Arrays.asList(englishName, ukrainianName));

            boolean isInsert = new SubjectDaoImpl().create(newSubject);
            if (!isInsert) {
                errorMessage = "Something went wrong! Required fields cannot be empty!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

            request.setAttribute("subject", newSubject);
            logger.info("Set the request attribute:subject --> " + newSubject);

            List<Subject> subjectList = new SubjectDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
            subjectList.sort(Subject.COMPARE_BY_ID);
            session.setAttribute("subjectList", subjectList);
            logger.info("Set the session attribute:subjectList --> " + subjectList);

            logger.info("SubjectCreateCommand finished");
            return Path.SUBJECTS;
        }
    }
}
