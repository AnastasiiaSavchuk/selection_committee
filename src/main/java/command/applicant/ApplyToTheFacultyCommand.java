package command.applicant;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.GradeDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.*;
import domain.enums.ApplicationStatus;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Create new application and insert into db.
 *
 * @author A.Savchuk
 */
public class ApplyToTheFacultyCommand extends Command {
    private static final long serialVersionUID = 6532589541252014556L;
    private static final Logger logger = Logger.getLogger(ApplyToTheFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplyToTheFacultyCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        Faculty faculty = (Faculty) session.getAttribute("faculty");

        List<Grade> gradesForApply = new GradeDaoImpl().readApplicantGradesByFacultyId(faculty.getId(), applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        Application newApplication = new Application();
        newApplication.setApplicant(applicant);
        newApplication.setFaculty(faculty);
        newApplication.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
        newApplication.setGradeList(gradesForApply);

        boolean isInsert = new ApplicationDaoImpl().create(newApplication);
        if (!isInsert) {
            errorMessage = "Unable to create new application!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        boolean isInsertApplicationGrade = new GradeDaoImpl().createApplicationGrade(newApplication);
        if (!isInsertApplicationGrade) {
            errorMessage = "Unable to create grades to application!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        List<Grade> gradeList = new GradeDaoImpl().readGradesByApplicantId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        session.setAttribute("gradeList", gradeList);
        logger.info("Set the session attribute:gradeList --> " + gradeList);

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("ApplyToTheFacultyCommand finished");
        return Path.APPLICANT;
    }
}