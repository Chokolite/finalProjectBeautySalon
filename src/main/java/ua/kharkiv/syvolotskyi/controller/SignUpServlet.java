package ua.kharkiv.syvolotskyi.controller;


import ua.kharkiv.syvolotskyi.controller.common.ConverterUtils;
import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
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

@WebServlet(urlPatterns = "/sign-up")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspConstants.SIGN_UP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = ConverterUtils.convertRequestToAdmin(request);
        if (userService.existsByEmail(user.getEmail())) {
            ValidationException.builder().put("emailError", ValidationEnum.EMAIL_EXISTS).throwIfErrorExists();
        }
        user.setId(userService.save(user));
        HttpSession session = request.getSession();
        session.setAttribute(String.valueOf(Role.ADMIN), user);
        response.sendRedirect("/admin/admin-home");
    }
}
