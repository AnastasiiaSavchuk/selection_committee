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
    private static final long serialVersionUID = 1874599665125478541L;
    private static final Logger logger = Logger.getLogger(SetLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("LanguageSetCommand starts");

        HttpSession session = request.getSession();
        String locale = request.getParameter("language");
        logger.info("Request parameter:locale --> " + locale);
        session.setAttribute("elanguage", locale);

        logger.info("LanguageSetCommand finished");
        return Path.INDEX;
    }
}