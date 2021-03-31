package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.repository.AppointmentRepository;
import ua.kharkiv.syvolotskyi.repository.CatalogRepository;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.service.impl.AppointmentServiceImpl;
import ua.kharkiv.syvolotskyi.service.impl.CatalogServiceImpl;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAppointmentServletTest {
    private CreateAppointmentServlet createAppointmentServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;


    @Before
    public void setUp() {
        createAppointmentServlet = new CreateAppointmentServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void doGet() throws IOException, ServletException {
        when(request.getRequestDispatcher(JspConstants.CREATE_APPOINTMENT_JSP)).thenReturn(dispatcher);
        createAppointmentServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.CREATE_APPOINTMENT_JSP);
        verify(dispatcher).forward(request, response);
    }
}
