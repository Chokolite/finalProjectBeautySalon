package ua.kharkiv.syvolotskyi.filter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;

public class LocaleFilter implements Filter {
    private static final String[] languages = {"en", "ru"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Locale locale = defineLocale(request);

        ServletRequest requestWrapper = setLocaleInRequest((HttpServletRequest) request, locale);

        chain.doFilter(requestWrapper, response);
    }

    private HttpServletRequestWrapper setLocaleInRequest(HttpServletRequest request, Locale locale) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return Collections.enumeration(Collections.singleton(locale));
            }
        };
    }

    private Locale defineLocale(ServletRequest request) {
        String strLocale = request.getParameter("language");
        Locale locale = null;
        if (StringUtils.isNotBlank(strLocale)) {
            locale = Locale.forLanguageTag(strLocale);
        } else if (Objects.nonNull(((HttpServletRequest) request).getSession(true).getAttribute("locale"))) {
            locale = (Locale) ((HttpServletRequest) request).getSession(true).getAttribute("locale");
        } else {
            Enumeration<Locale> locales = request.getLocales();
            while (locales.hasMoreElements()) {
                Locale requestLocale = locales.nextElement();
                if (ArrayUtils.contains(languages, requestLocale.getLanguage())) {
                    locale = requestLocale;
                    break;
                }
            }
            if (Objects.isNull(locale)) {
                locale = Locale.ENGLISH;
            }
        }
        ((HttpServletRequest) request).getSession().setAttribute("locale", locale);
        return locale;
    }

    @Override
    public void destroy() {

    }
}
