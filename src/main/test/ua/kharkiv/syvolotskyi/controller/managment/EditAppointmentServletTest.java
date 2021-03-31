package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class EditAppointmentServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private EditAppointmentServlet editAppointmentServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        editAppointmentServlet = new EditAppointmentServlet();
    }


    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(JspConstants.EDIT_APPOINTMENTS_JSP)).thenReturn(dispatcher);
        editAppointmentServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.EDIT_APPOINTMENTS_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost() throws IOException {
        editAppointmentServlet.doPost(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }
}