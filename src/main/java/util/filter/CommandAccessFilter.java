package util.filter;

import domain.enums.Role;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter. Disabled by default. Uncomment Security filter
 * section in web.xml to enable.
 *
 * @author A.Savchuk
 */
public class CommandAccessFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CommandAccessFilter.class);

    // commands access
    private static Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    public void destroy() {
        logger.info("Filter destruction starts");
        // do nothing
        logger.info("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Filter starts");

        if (accessAllowed(request)) {
            logger.info("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessages);
            logger.trace("Set the request attribute: errorMessage --> " + errorMessages);

            request.getRequestDispatcher(Path.ERROR).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty())
            return false;

        if (outOfControl.contains(commandName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        Role userRole = (Role) session.getAttribute("applicantRole");
        if (userRole == null)
            return false;

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) {
        logger.info("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("ADMIN")));
        accessMap.put(Role.USER, asList(fConfig.getInitParameter("USER")));
        logger.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("COMMON"));
        logger.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("OUT-OF-CONTROL"));
        logger.trace("Out of control commands --> " + outOfControl);

        logger.info("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
