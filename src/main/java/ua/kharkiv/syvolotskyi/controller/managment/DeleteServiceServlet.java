package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.service.ServiceService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-service")
public class DeleteServiceServlet extends HttpServlet {
    private ServiceService serviceService;

    @Override
    public void init(ServletConfig config) {
        serviceService = (ServiceService) config.getServletContext().getAttribute(ServiceService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (serviceService != null) {
            Service service = serviceService.getById(Long.valueOf(request.getParameter("id")));
            serviceService.delete(service.getId());
        }
        response.sendRedirect("/admin/admin-home");
    }

}
