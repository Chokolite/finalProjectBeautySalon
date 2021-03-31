package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.CatalogService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/delete-catalog", "/master/delete-catalog"})
public class DeleteCatalogServlet extends HttpServlet {
    CatalogService catalogService;

    @Override
    public void init(ServletConfig config) {
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (catalogService != null) {
            Catalog catalog = catalogService.getById(Long.valueOf(request.getParameter("id")));

            if (Role.ADMIN.equals(user.getRole())) {
                catalogService.delete(catalog.getId());
            }
            if (catalog.getMaster().getId().equals(user.getId())) {
                catalogService.delete(catalog.getId());
            }
        }

        if (Role.ADMIN.equals(user.getRole())) {
            response.sendRedirect("/admin/admin-home");
        }
        if (Role.MASTER.equals(user.getRole())) {
            response.sendRedirect("/master/master-home");
        }
    }
}
