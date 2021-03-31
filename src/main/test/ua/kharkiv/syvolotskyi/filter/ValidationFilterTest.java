package ua.kharkiv.syvolotskyi.filter;

import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ValidationFilterTest {

    @Test
    public void doFilter() throws IOException, ServletException {
        ValidationFilter validationFilter = new ValidationFilter();
        FilterChain mockChain = mock(FilterChain.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("errors")).thenReturn("some error");
        validationFilter.doFilter(request, response, mockChain);
        verify(mockChain).doFilter(request, response);
    }
}