package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DeleteCatalogServletTest {
    private DeleteCatalogServlet deleteCatalogServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        deleteCatalogServlet = new DeleteCatalogServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenCallDoGetSendRedirectToAdminHome() throws IOException {
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(Role.ADMIN);
        deleteCatalogServlet.doGet(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }

    @Test
    public void whenCallDoGetSendRedirectToMasterHome() throws IOException {
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(user.getRole()).thenReturn(Role.MASTER);
        deleteCatalogServlet.doGet(request, response);
        verify(response).sendRedirect("/master/master-home");
    }
}