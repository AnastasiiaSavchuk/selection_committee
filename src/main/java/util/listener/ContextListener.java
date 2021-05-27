package util.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Context listener.
 *
 * @author A.Savchuk
 */
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger logger = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {
        log("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();
        initI18N(servletContext);

        log("Servlet context initialization finished");
    }

    /**
     * Initializes i18n subsystem.
     */
    private void initI18N(ServletContext servletContext) {
        logger.info("I18N subsystem initialization started");

        String localesValue = servletContext.getInitParameter("locales");
        if (localesValue == null || localesValue.isEmpty()) {
            logger.warn("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> locales = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                locales.add(localeName);
            }
            logger.info("Application attribute set: locales : " + locales);
            servletContext.setAttribute("locales", locales);
        }
        logger.info("I18N subsystem initialization finished");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext defines a set of methods that a servlet uses to
     *                       communicate with its servlet container
     */
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("META-INF/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {
        logger.info("Command container initialization started");
        // initialize commands container
        // just load class to JVM
        try {
            Class.forName("command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        logger.info("Command container initialization finished");
    }

    private void log(String msg) {
        System.out.println("[utils.ContextListener] " + msg);
    }
}
