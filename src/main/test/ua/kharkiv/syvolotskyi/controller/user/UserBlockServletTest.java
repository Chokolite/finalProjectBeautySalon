package ua.kharkiv.syvolotskyi.controller.user;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserBlockServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private UserBlockServlet userBlockServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        userBlockServlet = new UserBlockServlet();
    }
    @Test
    public void doPost() throws IOException {
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("enabled")).thenReturn(String.valueOf(anyBoolean()));
        userBlockServlet.doPost(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }
}