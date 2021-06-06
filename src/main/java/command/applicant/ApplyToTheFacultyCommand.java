package command.applicant;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import dao.impl.GradeDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.Applicant;
import domain.Application;
import domain.Faculty;
import domain.Grade;
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

public class ApplyToTheFacultyCommand extends Command {
    private static final long serialVersionUID = 7854123658942158741L;
    private static final Logger logger = Logger.getLogger(ApplyToTheFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplyToTheFacultyCommand starts");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        String[] subjectIdList = request.getParameterValues("subjectId");
        String[] gradeValueList = request.getParameterValues("grade");

        if (Objects.isNull(subjectIdList)) {
            errorMessage = "Something went wrong! SubjectId cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (Objects.isNull(gradeValueList)) {
            errorMessage = "Something went wrong! Please enter your ZNO grades!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
            List<Grade> gradeList = new ArrayList<>();

            for (int i = 0; i < subjectIdList.length; i++) {
                Grade newGrade = new Grade();
                newGrade.setApplicant(applicant);
                newGrade.setSubject(new SubjectDaoImpl().readById(Integer.parseInt(subjectIdList[i]), Collections.singletonList(language == null ? localeLang : language)));
                newGrade.setGrade(Integer.parseInt(gradeValueList[i]));
                gradeList.add(new GradeDaoImpl().createGrade(newGrade));
            }

            Application application = new Application();
            application.setApplicant(applicant);
            application.setFaculty(faculty);
            application.setApplicationStatus(ApplicationStatus.IN_PROCESSING);
            application.setGradeList(gradeList);

            boolean isInsert = new ApplicationDaoImpl().create(application);
            if (!isInsert) {
                errorMessage = "Something went wrong! Unable to create new application!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

            boolean isInsertApplicationGrade = new GradeDaoImpl().createApplicationGrade(application);
            if (!isInsertApplicationGrade) {
                errorMessage = "Something went wrong! Unable to create grades to application!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

        }

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationsByUserId(applicant.getId(), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("ApplyToTheFacultyCommand finished");
        return Path.APPLICANT;
    }
}