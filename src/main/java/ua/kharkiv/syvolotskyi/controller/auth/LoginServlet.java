package ua.kharkiv.syvolotskyi.controller.auth;

import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
import ua.kharkiv.syvolotskyi.controller.common.PasswordEncoder;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.exception.ValidationEnum;
import ua.kharkiv.syvolotskyi.exception.ValidationException;
import ua.kharkiv.syvolotskyi.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.USER_LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));
        User user = userService.getUserByEmailAndPassword(email, password);
        if (Objects.nonNull(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if(Role.ADMIN.equals(user.getRole())){
                response.sendRedirect("/admin/admin-home");
            }
            if(Role.MASTER.equals(user.getRole())){
                response.sendRedirect("/master/master-home");
            }
            if(Role.CLIENT.equals(user.getRole())) {
                response.sendRedirect("/client/client-home");
            }
        } else {
            ValidationException.builder().put("loginError", ValidationEnum.BAD_CREDENTIAL).throwIfErrorExists();
        }
    }
}