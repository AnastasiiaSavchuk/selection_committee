package command.admin;

import command.Command;
import dao.ApplicantDao;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Get list of applicants from db and show admin page.
 *
 * @author A.Savchuk
 */
public class AdminCommand extends Command {
    private static final long serialVersionUID = 4512365478965414141L;
    private static final Logger logger = Logger.getLogger(AdminCommand.class);
    private static final ApplicantDao APPLICANT_DAO = new ApplicantDaoImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("AdminPageCommand started");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Applicant> applicantList = APPLICANT_DAO.readAll(Collections.singletonList(language == null ? localeLang : language));
        applicantList.sort(Applicant.COMPARE_BY_ID);
        session.setAttribute("applicantList", applicantList);
        logger.info("Set the session attribute to admin page:applicantList --> " + applicantList);

        logger.debug("AdminPageCommand finished ");
        return Path.ADMIN;
    }
}
