package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.utils.ConverterUtils;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.CatalogService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/create-catalog", "/master/create-catalog"})
public class CreateCatalogServlet extends HttpServlet {
    private CatalogService catalogService;

    @Override
    public void init(ServletConfig config) {
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        catalogService.save(ConverterUtils.convertRequestToCatalog(request));
        System.out.println(request.getParameter("masterId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(Role.ADMIN.equals(user.getRole())) {
            response.sendRedirect("/admin/admin-home");
        }
        if(Role.MASTER.equals(user.getRole())) {
            response.sendRedirect("/master/master-home");
        }
    }
}
