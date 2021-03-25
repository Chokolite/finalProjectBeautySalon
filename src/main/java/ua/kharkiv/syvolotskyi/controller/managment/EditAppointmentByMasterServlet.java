package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.controller.common.ConverterUtils;
import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
import ua.kharkiv.syvolotskyi.controller.common.SendMail;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Status;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/master/master-edit-appointment")
public class EditAppointmentByMasterServlet extends HttpServlet {
    private AppointmentService appointmentService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Appointment appointment = appointmentService.getById(Long.parseLong(request.getParameter("appointmentId")));
        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher(JspConstants.EDIT_APPOINTMENTS_BY_MASTER_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Appointment appointment = ConverterUtils.convertRequestToAppointment(request);

        if (Status.COMPLETE.equals(appointment.getStatus())) {
            User client = userService.getById(Long.valueOf(request.getParameter("clientId")));
            SendMail sendMail = new SendMail(client, LocalDateTime.now());
            sendMail.shelude();
        }
        appointmentService.update(appointment);
        response.sendRedirect("/master/master-home");
    }
}