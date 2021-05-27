package util.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 *
 * @author A.Savchuk
 */
public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);
    private String encoding;

    public void destroy() {
        logger.info("Filter destruction starts");
        // do nothing
        logger.info("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            logger.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        logger.info("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
        logger.info("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        logger.trace("Encoding from web.xml --> " + encoding);
        logger.info("Filter initialization finished");
    }

}