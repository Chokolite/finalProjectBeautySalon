package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.controller.common.ConverterUtils;
import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/create-user")
public class CreateUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.CREATE_USER_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role = Role.valueOf(request.getParameter("role"));
        if(Role.CLIENT.equals(role)){
            userService.save(ConverterUtils.convertRequestToClient(request));
        }
        if(Role.ADMIN.equals(role)) {
            userService.save(ConverterUtils.convertRequestToAdmin(request));
        }
        if(Role.MASTER.equals(role)){
            userService.save(ConverterUtils.convertRequestToMaster(request));
        }
        response.sendRedirect("/admin/admin-home");
    }
}
