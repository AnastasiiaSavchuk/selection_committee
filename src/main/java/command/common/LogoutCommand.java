package command.common;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout command and invalidate session.
 *
 * @author A.Savchuk
 */
public class LogoutCommand extends Command {
    private static final long serialVersionUID = 6548605435104684648L;
    private static final Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("LogoutCommand starts");

        request.getSession(false).invalidate();

        logger.info("LogoutCommand finished");
        return Path.INDEX;
    }
}
