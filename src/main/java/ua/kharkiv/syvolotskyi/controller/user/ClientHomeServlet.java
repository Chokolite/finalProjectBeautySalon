package ua.kharkiv.syvolotskyi.controller.user;

import ua.kharkiv.syvolotskyi.utils.JspConstants;
import ua.kharkiv.syvolotskyi.utils.PaginationUtils;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/client/client-home")
public class ClientHomeServlet extends HttpServlet {
    private CatalogService catalogService;
    private AppointmentService appointmentService;

    @Override
    public void init(ServletConfig config){
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String masterName = request.getParameter("masterName");
        int offset = PaginationUtils.getOffset(request);
        int size = PaginationUtils.getSize(request);

        if(name != null) {
            List<Catalog> catalogList = catalogService.getAll(name, type, request.getParameter("order"), offset, size);
            List<Appointment> appointmentList = appointmentService.getAll();
            List<Appointment> myAppointments = new ArrayList<>();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            for (Appointment al : appointmentList) {
                if (al.getClient().getId() != null && al.getClient().getId().equals(user.getId())) {
                    myAppointments.add(al);
                }
            }
            request.setAttribute("catalogs", catalogList);
            request.setAttribute("appointments", myAppointments);
            request.setAttribute("catalogSize", catalogService.getCount(masterName));

        }
        request.getRequestDispatcher(JspConstants.HOMEPAGE_CLIENT_JSP).forward(request, response);
    }
}
