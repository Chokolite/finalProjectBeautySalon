package ua.kharkiv.syvolotskyi.controller.user;

import ua.kharkiv.syvolotskyi.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/user-update-enabled")
public class UserBlockServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("userId"));
        Boolean enabled = Boolean.valueOf(req.getParameter("enabled"));
        userService.updateEnabled(id, enabled);
        resp.sendRedirect("/admin/admin-home");
    }
}