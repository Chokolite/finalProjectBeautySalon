package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.controller.managment.CreateUserServlet;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.service.UserService;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CreateUserServletTest {
    CreateUserServlet createUserServlet;
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        createUserServlet = new CreateUserServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(JspConstants.CREATE_USER_JSP)).thenReturn(dispatcher);
        createUserServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.CREATE_USER_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost() throws IOException {
        when(request.getParameter("role")).thenReturn(Role.GUEST.toString());
        createUserServlet.doPost(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }
}
