package ua.kharkiv.syvolotskyi.controller.user;

import ua.kharkiv.syvolotskyi.utils.JspConstants;
import ua.kharkiv.syvolotskyi.utils.PaginationUtils;
import ua.kharkiv.syvolotskyi.utils.TimeSlots;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.service.ServiceService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/master/master-home")
public class MasterHomeServlet extends HttpServlet {
    private ServiceService serviceService;
    private CatalogService catalogService;
    private AppointmentService appointmentService;

    @Override
    public void init(ServletConfig config) {
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
        List<Appointment> appointmentList = appointmentService.getAll();
        List<Service> serviceList = serviceService.getAll();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        TimeSlots timeSlots = new TimeSlots(appointmentList, user.getId());
        Map<LocalDateTime, Map<LocalDateTime, Boolean>> shelude = timeSlots.createShelude();

        request.setAttribute("appointments", appointmentList);
        request.setAttribute("shelude", shelude);
        request.setAttribute("services", serviceList);
        request.setAttribute("catalogs", catalogList);

        request.getRequestDispatcher(JspConstants.HOMEPAGE_MASTER_JSP).forward(request, response);
    }
}
