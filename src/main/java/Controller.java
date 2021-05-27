import command.Command;
import command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main Servlet controller of the application
 *
 * @author A.Savchuk
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 2423353715955164816L;
    private static final Logger logger = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        logger.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        logger.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = command.execute(request, response);
        logger.trace("Forward address --> " + forward);

        logger.info("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
            dispatcher.forward(request, response);
        }
    }
}
