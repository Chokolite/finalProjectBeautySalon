package ua.kharkiv.syvolotskyi;

import org.junit.Test;
import ua.kharkiv.syvolotskyi.controller.user.AdminHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.ClientHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.GuestHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.MasterHomeServlet;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class GetUserHomePageServletTest {

    @Test
    public void whenCallDoGetThenServletReturnAdminHomePage() throws ServletException, IOException {
        final AdminHomeServlet adminHomeServlet = new AdminHomeServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_ADMIN_JSP)).thenReturn(dispatcher);
        adminHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_ADMIN_JSP);
        verify(dispatcher).forward(request, response);
    }
    @Test
    public void whenCallDoGetThenServletReturnMasterHomePage() throws ServletException, IOException {
        final MasterHomeServlet masterHomeServlet = new MasterHomeServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_MASTER_JSP)).thenReturn(dispatcher);
        masterHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_MASTER_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCallDoGetThenServletReturnClientHomePage() throws ServletException, IOException {
        final ClientHomeServlet clientHomeServlet = new ClientHomeServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_CLIENT_JSP)).thenReturn(dispatcher);
        clientHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_CLIENT_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCallDoGetThenServletReturnGuestHomePage() throws ServletException, IOException {
        final GuestHomeServlet guestHomeServlet = new GuestHomeServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_GUEST_JSP)).thenReturn(dispatcher);
        guestHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_GUEST_JSP);
        verify(dispatcher).forward(request, response);
    }
}
