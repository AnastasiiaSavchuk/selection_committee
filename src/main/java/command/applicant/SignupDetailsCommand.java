package command.applicant;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.GradeDaoImpl;
import dao.impl.SubjectDaoImpl;
import domain.Applicant;
import domain.Grade;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Objects;

/**
 * Get all details from request and save applicant details for finished registration.
 *
 * @author A.Savchuk.
 */
@MultipartConfig(maxFileSize = 4194304)
public class SignupDetailsCommand extends Command {
    private static final long serialVersionUID = 2541032598652301457L;
    private static final Logger logger = Logger.getLogger(SignupDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SignupDetailsCommand started");
        String errorMessage;

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        Applicant sessionApplicant = (Applicant) session.getAttribute("applicant");
        int applicantId = sessionApplicant.getId();
        String email = sessionApplicant.getEmail();

        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String schoolName = request.getParameter("schoolName");
        String[] subjectIdList = request.getParameterValues("subjectId");
        String[] gradeValueList = request.getParameterValues("grade");

        if (firstName == null || middleName == null || lastName == null || city == null || region == null
                || schoolName == null) {
            errorMessage = "Required fields cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (Objects.isNull(subjectIdList)) {
            errorMessage = "SubjectId cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else if (Objects.isNull(gradeValueList)) {
            errorMessage = "Please enter your ZNO grades!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        } else {
            Applicant applicant = new Applicant();
            applicant.setId(applicantId);
            applicant.setEmail(email);
            applicant.setFirstName(firstName);
            applicant.setMiddleName(middleName);
            applicant.setLastName(lastName);
            applicant.setCity(city);
            applicant.setRegion(region);
            applicant.setSchoolName(schoolName);
            applicant.setCertificate(new byte[]{});
            applicant.setBlocked(false);

            boolean isInsert = new ApplicantDaoImpl().create(applicant);
            if (!isInsert) {
                errorMessage = "Unable to create new applicant!";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR;
            }

            for (int i = 0; i < subjectIdList.length; i++) {
                Grade newGrade = new Grade();
                newGrade.setApplicant(applicant);
                newGrade.setSubject(new SubjectDaoImpl().readById(Integer.parseInt(subjectIdList[i]), Collections.singletonList(language == null ? localeLang : language)));
                newGrade.setGrade(Integer.parseInt(gradeValueList[i]));
                new GradeDaoImpl().createGrade(newGrade);
            }

            session.setAttribute("applicant", applicant);
            logger.info("Set the session attribute:applicant --> " + applicant);

            logger.info("SignupDetailsCommand finished");
            return Path.SAVE_CERTIFICATE;
        }
    }
}
