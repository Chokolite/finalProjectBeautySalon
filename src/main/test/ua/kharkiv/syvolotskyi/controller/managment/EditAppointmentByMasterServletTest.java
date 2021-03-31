package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class EditAppointmentByMasterServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private EditAppointmentByMasterServlet editAppointmentByMasterServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        editAppointmentByMasterServlet = new EditAppointmentByMasterServlet();
    }


    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(JspConstants.EDIT_APPOINTMENTS_BY_MASTER_JSP)).thenReturn(dispatcher);
        editAppointmentByMasterServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.EDIT_APPOINTMENTS_BY_MASTER_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost() throws IOException {
        editAppointmentByMasterServlet.doPost(request, response);
        verify(response).sendRedirect("/master/master-home");
    }
}