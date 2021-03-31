package ua.kharkiv.syvolotskyi.controller.user;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.service.ServiceService;
import ua.kharkiv.syvolotskyi.service.UserService;
import ua.kharkiv.syvolotskyi.service.impl.CatalogServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserBlockServletTest {
    private ServletConfig servletConfigMock;
    private ServletContext servletContextMock;
    private UserService userServiceMock;
    private ServiceService serviceServiceMock;
    private CatalogService catalogService;
    private AppointmentService appointmentServiceMock;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private UserBlockServlet userBlockServlet;

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
        userBlockServlet = new UserBlockServlet();

        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getAttribute(UserService.class.toString())).thenReturn(userServiceMock);
        when(servletContextMock.getAttribute(ServiceService.class.toString())).thenReturn(serviceServiceMock);
        when(servletContextMock.getAttribute(CatalogService.class.toString())).thenReturn(catalogService);
        when(servletContextMock.getAttribute(AppointmentService.class.toString())).thenReturn(appointmentServiceMock);
        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
    }

    @Test
    public void doPost() throws IOException {
        userBlockServlet.init(servletConfigMock);
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("enabled")).thenReturn(String.valueOf(true));
        doNothing().when(userServiceMock).updateEnabled(1L, true);
        userBlockServlet.doPost(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }
}