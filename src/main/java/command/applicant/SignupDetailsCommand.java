package command.applicant;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.FacultyDaoImpl;
import domain.Applicant;
import domain.Faculty;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

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
        logger.info("SignupDetailsCommand starts");
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

        if (firstName == null || middleName == null || lastName == null || city == null || region == null
                || schoolName == null) {
            errorMessage = "Required fields cannot be empty!";
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

            session.setAttribute("applicant", applicant);
            logger.info("Set the session attribute:applicant --> " + applicant);


            List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
            facultyList.sort(Faculty.COMPARE_BY_ID);
            session.setAttribute("facultyList", facultyList);

            logger.info("SignupDetailsCommand finished");
            return Path.FACULTIES;
        }
    }
}
