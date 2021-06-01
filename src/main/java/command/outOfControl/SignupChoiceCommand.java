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
    private static final long serialVersionUID = 1666483864804192503L;
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("RegistrationChoiceCommand finished");
        return Path.SIGNUP;
    }
}