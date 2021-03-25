package ua.kharkiv.syvolotskyi.controller.user;

import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
import ua.kharkiv.syvolotskyi.controller.common.PaginationUtils;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.service.ServiceService;
import ua.kharkiv.syvolotskyi.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/admin-home")
public class AdminHomeServlet extends HttpServlet {
    private UserService userService;
    private ServiceService serviceService;
    private CatalogService catalogService;
    private AppointmentService appointmentService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
        serviceService = (ServiceService) config.getServletContext().getAttribute(ServiceService.class.toString());
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int offset = PaginationUtils.getOffset(request);
        int size = PaginationUtils.getSize(request);

        List<Catalog> catalogList = catalogService.getAll(name, type, request.getParameter("order"), offset, size);
        List<User> userList = userService.getAll();
        List<Service> serviceList = serviceService.getAll();
        List<Appointment> appointmentList = appointmentService.getAll();

        request.setAttribute("users", userList);
        request.setAttribute("services", serviceList);
        request.setAttribute("catalogs", catalogList);
        request.setAttribute("appointments", appointmentList);

        request.getRequestDispatcher(JspConstants.HOMEPAGE_ADMIN_JSP).forward(request, response);
    }
}
