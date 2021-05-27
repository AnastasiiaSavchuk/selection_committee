package command.outOfControl;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Forward request to login page
 */
public class LoginChoiceCommand extends Command {
    private static final long serialVersionUID = -2883918115553516296L;
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("LoginChoiceCommand finished");
        return Path.LOGIN;
    }
}