package ua.kharkiv.syvolotskyi.controller.user;

import ua.kharkiv.syvolotskyi.utils.JspConstants;
import ua.kharkiv.syvolotskyi.utils.PaginationUtils;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.service.CatalogService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/guest/guest-home")
public class GuestHomeServlet extends HttpServlet {
    private CatalogService catalogService;

    @Override
    public void init(ServletConfig config) {
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int offset = PaginationUtils.getOffset(request);
        int size = PaginationUtils.getSize(request);
        if (name != null) {
            List<Catalog> catalogList = catalogService.getAll(name, type, request.getParameter("order"), offset, size);
            request.setAttribute("catalogs", catalogList);
        }
        request.getRequestDispatcher(JspConstants.HOMEPAGE_GUEST_JSP).forward(request, response);
    }
}
