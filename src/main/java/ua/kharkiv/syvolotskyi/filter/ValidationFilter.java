package ua.kharkiv.syvolotskyi.filter;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.exception.ValidationException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class ValidationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(ValidationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("errors") != null) {
            request.setAttribute("errors", session.getAttribute("errors"));
            session.removeAttribute("errors");
        }
        try {
            filterChain.doFilter(request, response);
        } catch (ValidationException ex) {
            LOG.debug("validation exception", ex);
            httpRequest.getSession().setAttribute("errors", ex.getErrors());
            String parameterReferer = httpRequest.getParameter("errorRedirect");
            String redirect = Objects.nonNull(parameterReferer) ? parameterReferer : httpRequest.getHeader("referer");
            ((HttpServletResponse) response).sendRedirect(redirect);
        }
    }

    @Override
    public void destroy() {

    }
}
