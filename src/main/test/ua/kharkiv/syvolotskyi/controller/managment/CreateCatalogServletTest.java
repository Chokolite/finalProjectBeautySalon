package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class CreateCatalogServletTest {
    private CreateCatalogServlet createCatalogServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;


    @Before
    public void setUp() {
        createCatalogServlet = new CreateCatalogServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenCallDoThenRedirectToAdminHomePage() throws ServletException, IOException {
        HttpSession httpSession = mock(HttpSession.class);
        String adminHome = "/admin/admin-home";
        User user = mock(User.class);
        user.setRole(Role.ADMIN);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(Role.ADMIN);
        createCatalogServlet.doGet(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }

    @Test
    public void whenCallDoThenRedirectToMasterHomePage() throws ServletException, IOException {
        HttpSession httpSession = mock(HttpSession.class);
        User user = mock(User.class);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(Role.MASTER);
        createCatalogServlet.doGet(request, response);
        verify(response).sendRedirect("/master/master-home");
    }
}