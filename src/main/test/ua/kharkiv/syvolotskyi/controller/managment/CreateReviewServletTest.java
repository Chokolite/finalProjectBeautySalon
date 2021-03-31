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

public class CreateReviewServletTest {
    CreateReviewServlet createReviewServlet;
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        createReviewServlet = new CreateReviewServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(JspConstants.CREATE_REVIEW_JSP)).thenReturn(dispatcher);
        createReviewServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.CREATE_REVIEW_JSP);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost() throws IOException {
        createReviewServlet.doPost(request, response);
        verify(response).sendRedirect("/client/client-home");
    }
}