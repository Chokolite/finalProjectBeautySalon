package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.utils.ConverterUtils;
import ua.kharkiv.syvolotskyi.utils.JspConstants;
import ua.kharkiv.syvolotskyi.utils.impl.TimeSlotsImpl;

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

@WebServlet(urlPatterns = {"/admin/create-appointment", "/client/create-appointment"})
public class CreateAppointmentServlet extends HttpServlet {
    private AppointmentService appointmentService;
    private CatalogService catalogService;

    @Override
    public void init(ServletConfig config) {
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
        catalogService = (CatalogService) config.getServletContext().getAttribute(CatalogService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Appointment> appointmentList = appointmentService.getAll();
        request.setAttribute("appointments", appointmentList);

        Long masterId = catalogService.getById(Long.valueOf(request.getParameter("catalogId"))).getMaster().getId();
        TimeSlotsImpl timeSlots = new TimeSlotsImpl();
        Map<LocalDateTime, Map<LocalDateTime, Boolean>> schedule = timeSlots.createShelude(appointmentList, masterId);

        request.setAttribute("schedule", schedule);
        request.getRequestDispatcher(JspConstants.CREATE_APPOINTMENT_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Appointment appointment = ConverterUtils.convertRequestToAppointment(request);
        appointmentService.save(appointment);
        if(Role.ADMIN.equals(user.getRole())){
            response.sendRedirect("/admin/admin-home");
        }
        if(Role.CLIENT.equals(user.getRole())) {
            response.sendRedirect("/client/client-home");
        }
    }
}
