package command.applicant;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import dao.impl.FacultyDaoImpl;
import domain.Applicant;
import domain.Faculty;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Receive certificate data a form request and save into applicant in the db
 */
@MultipartConfig(maxFileSize = 4194304)
public class SaveCertificateCommand extends Command {

    private static final long serialVersionUID = 6587412589632541256L;
    private static final Logger logger = Logger.getLogger(SaveCertificateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SaveCertificateCommand started --> ");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        Applicant applicant = (Applicant) session.getAttribute("applicant");

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            String errorMessage = "Certificate image cannot be empty!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4194304);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            byte[] uploadedCertificate = null;
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                uploadedCertificate = IOUtils.toByteArray(item.getInputStream());
            }

            boolean isCertificateInserted = new ApplicantDaoImpl().updateCertificate(applicant.getId(), uploadedCertificate);
            if (isCertificateInserted) {
                String certImage = Base64.getEncoder().encodeToString(uploadedCertificate);
                session.setAttribute("certImage", certImage);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        List<Faculty> facultyList = new FacultyDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        facultyList.sort(Faculty.COMPARE_BY_ID);
        session.setAttribute("facultyList", facultyList);

        logger.debug("SaveCertificateCommand finished");
        return Path.FACULTIES;
    }
}