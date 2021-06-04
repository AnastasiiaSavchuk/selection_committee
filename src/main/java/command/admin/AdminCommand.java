package command.admin;

import command.Command;
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
 * Retrieve list of applicants from db and show admin page.
 *
 * @author A.Savchuk
 */
public class AdminCommand extends Command {
    private static final long serialVersionUID = 688942586252025570L;
    private static final Logger logger = Logger.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("AdminPageCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");

        List<Applicant> applicantList = new ApplicantDaoImpl().readAll(Collections.singletonList(language == null ? localeLang : language));
        applicantList.sort(Applicant.COMPARE_BY_ID);
        session.setAttribute("applicantList", applicantList);
        logger.info("Set the session attribute to admin page:applicantList --> " + applicantList);

        logger.debug("AdminPageCommand finished ");
        return Path.ADMIN;
    }
}
