package ua.kharkiv.syvolotskyi.controller.managment;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteServiceServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private DeleteServiceServlet deleteServiceServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        deleteServiceServlet = new DeleteServiceServlet();
    }
    @Test
    public void doGet() throws IOException {
        deleteServiceServlet.doGet(request, response);
        verify(response).sendRedirect("/admin/admin-home");
    }
}