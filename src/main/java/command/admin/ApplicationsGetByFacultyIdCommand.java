package command.admin;

import command.Command;
import dao.impl.ApplicationDaoImpl;
import domain.Application;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Get application from db by faculty id.
 *
 * @author A.Savchuk
 */
public class ApplicationsGetByFacultyIdCommand extends Command {
    private static final long serialVersionUID = 4587563215984512124L;
    private static final Logger logger = Logger.getLogger(ApplicationsGetByFacultyIdCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ApplicationsGetByFacultyIdCommand starts");

        HttpSession session = request.getSession();
        String localeLang = request.getLocale().getLanguage();
        String language = (String) session.getAttribute("elanguage");
        String facultyId = request.getParameter("facultyId");

        List<Application> applicationList = new ApplicationDaoImpl().readApplicationByFacultyId(Integer.parseInt(facultyId), Collections.singletonList(language == null ? localeLang : language));
        applicationList.sort(Application.COMPARE_BY_ID);
        session.setAttribute("applicationList", applicationList);
        logger.info("Set the session attribute:applicationList --> " + applicationList);

        logger.info("ApplicationsGetByFacultyIdCommand finished");
        return Path.FACULTY;
    }
}