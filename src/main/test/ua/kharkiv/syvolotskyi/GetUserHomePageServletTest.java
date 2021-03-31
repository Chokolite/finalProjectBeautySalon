package ua.kharkiv.syvolotskyi;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.controller.auth.LoginServlet;
import ua.kharkiv.syvolotskyi.controller.user.AdminHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.ClientHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.GuestHomeServlet;
import ua.kharkiv.syvolotskyi.controller.user.MasterHomeServlet;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.service.ServiceService;
import ua.kharkiv.syvolotskyi.service.UserService;
import ua.kharkiv.syvolotskyi.service.impl.CatalogServiceImpl;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GetUserHomePageServletTest {
    private ServletConfig servletConfigMock;
    private ServletContext servletContextMock;
    private UserService userServiceMock;
    private ServiceService serviceServiceMock;
    private CatalogService catalogService;
    private AppointmentService appointmentServiceMock;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        servletConfigMock = mock(ServletConfig.class);
        userServiceMock = mock(UserService.class);
        servletContextMock = mock(ServletContext.class);
        serviceServiceMock = mock(ServiceService.class);
        catalogService = mock(CatalogServiceImpl.class);
        appointmentServiceMock = mock(AppointmentService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);

        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getAttribute(UserService.class.toString())).thenReturn(userServiceMock);
        when(servletContextMock.getAttribute(ServiceService.class.toString())).thenReturn(serviceServiceMock);
        when(servletContextMock.getAttribute(CatalogService.class.toString())).thenReturn(catalogService);
        when(servletContextMock.getAttribute(AppointmentService.class.toString())).thenReturn(appointmentServiceMock);
        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
    }

    @Test
    public void whenCallDoGetThenServletReturnAdminHomePage() throws ServletException, IOException {
        AdminHomeServlet adminHomeServlet = new AdminHomeServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        List<Catalog> catalogList = new ArrayList<>();
        adminHomeServlet.init(servletConfigMock);

        when(request.getParameter("order")).thenReturn("ASC");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("type")).thenReturn("type");
        when(request.getParameter("offset")).thenReturn("1");
        when(request.getParameter("size")).thenReturn("1");
        when(catalogService.getAll(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(catalogList);
        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_ADMIN_JSP)).thenReturn(dispatcher);
        adminHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_ADMIN_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void mockUserServiceInLoginController() throws ServletException {
        LoginServlet loginServletMock = mock(LoginServlet.class);
        ServletConfig servletConfigMock = mock(ServletConfig.class);
        ServletContext servletContextMock = mock(ServletContext.class);
        UserService userServiceMock = mock(UserService.class);
        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getAttribute(anyString())).thenReturn(userServiceMock);
        loginServletMock.init(servletConfigMock);

        when(userServiceMock.getUserByEmailAndPassword(anyString(), anyString())).thenReturn(mock(User.class));
    }

    @Test
    public void whenCallDoGetThenServletReturnMasterHomePage() throws ServletException, IOException {
        final MasterHomeServlet masterHomeServlet = new MasterHomeServlet();
        HttpSession sessionMock = mock(HttpSession.class);
        User userMock = mock(User.class);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("user")).thenReturn(userMock);
        masterHomeServlet.init(servletConfigMock);
        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_MASTER_JSP)).thenReturn(dispatcher);
        masterHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_MASTER_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCallDoGetThenServletReturnClientHomePage() throws ServletException, IOException {
        ClientHomeServlet clientHomeServlet = new ClientHomeServlet();
        HttpSession sessionMock = mock(HttpSession.class);
        User userMock = mock(User.class);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("user")).thenReturn(userMock);
        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_CLIENT_JSP)).thenReturn(dispatcher);
        clientHomeServlet.init(servletConfigMock);
        clientHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_CLIENT_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCallDoGetThenServletReturnGuestHomePage() throws ServletException, IOException {
        GuestHomeServlet guestHomeServlet = new GuestHomeServlet();
        List<Catalog> catalogList = new ArrayList<>();

        HttpSession sessionMock = mock(HttpSession.class);
        User userMock = mock(User.class);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("user")).thenReturn(userMock);
        when(catalogService.getAll(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(catalogList);
        when(request.getRequestDispatcher(JspConstants.HOMEPAGE_GUEST_JSP)).thenReturn(dispatcher);
        guestHomeServlet.init(servletConfigMock);
        guestHomeServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.HOMEPAGE_GUEST_JSP);
        verify(dispatcher).forward(request, response);
    }
}
