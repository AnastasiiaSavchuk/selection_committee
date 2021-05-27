package command.admin;

import command.Command;
import dao.impl.ApplicantDaoImpl;
import domain.Applicant;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class AdminPageCommand extends Command {
    private static final long serialVersionUID = 688942586252025570L;
    private static final Logger logger = Logger.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("AdminPageCommand starts");

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("elanguage");

        List<Applicant> applicantList = new ApplicantDaoImpl().readAll(Arrays.asList(language));
        logger.trace("Found in DB: applicantList --> " + applicantList);

        applicantList.sort(Applicant.COMPARE_BY_ID);

        // put user order beans list to request
        request.setAttribute("applicantList", applicantList);
        logger.trace("Set the request attribute: applicantList --> " + applicantList);

        logger.debug("AdminPageCommand finished forwarded to --> " + Path.ADMIN);
        return Path.ADMIN;
    }
}
