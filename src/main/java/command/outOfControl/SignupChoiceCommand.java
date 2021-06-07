package command.outOfControl;

import command.Command;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Forward request to signup page.
 *
 * @author A.Savchuk.
 */
public class SignupChoiceCommand extends Command {
    private static final long serialVersionUID = 1635895441254785521L;
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("RegistrationChoiceCommand finished");
        return Path.SIGNUP;
    }
}