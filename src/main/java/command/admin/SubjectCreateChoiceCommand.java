package command.admin;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Forward to create faculty page.
 *
 * @author A.Savchuk
 */
public class SubjectCreateChoiceCommand extends Command {
    private static final long serialVersionUID = 5741258963123654562L;
    private static final Logger logger = Logger.getLogger(SubjectCreateChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("SubjectCreateChoiceCommand finished");
        return Path.SUBJECT_CREATE;
    }
}
