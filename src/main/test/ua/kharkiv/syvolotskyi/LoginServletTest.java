package ua.kharkiv.syvolotskyi;

import org.junit.Test;
import ua.kharkiv.syvolotskyi.controller.auth.LoginServlet;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.repository.UserRepository;
import ua.kharkiv.syvolotskyi.service.UserService;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginServletTest {
    @Test
    public void whenCallDoGetLoginPage() throws ServletException, IOException, SQLException {
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(JspConstants.USER_LOGIN_JSP)).thenReturn(dispatcher);
        loginServlet.doGet(request, response);
        verify(request).getRequestDispatcher(JspConstants.USER_LOGIN_JSP);
        verify(dispatcher).forward(request, response);
    }
}
