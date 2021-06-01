package command.outOfControl;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Settle language to session which was preferred by user.
 *
 * @author A.Savchuk
 */
public class SetLanguageCommand extends Command {
    private static final long serialVersionUID = -5176355668225804896L;
    private static final Logger log = Logger.getLogger(SetLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("LanguageSetCommand starts");
        HttpSession session = request.getSession();

        String locale = request.getParameter("language");
        log.info("Request parameter: locale --> " + locale);

        String forward = Path.INDEX;
        session.setAttribute("elanguage", locale);

        log.info("LanguageSetCommand finished");
        return forward;
    }
}