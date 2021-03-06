package command.common;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 *
 * @author A.Savchuk
 */
public class NoCommand extends Command {
    private static final long serialVersionUID = 3595890684864658952L;
    private static final Logger logger = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Command started");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        logger.error("Set the request attribute:errorMessage --> " + errorMessage);

        logger.info("Command finished");
        return Path.ERROR;
    }
}