package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.utils.ConverterUtils;
import ua.kharkiv.syvolotskyi.utils.JspConstants;
import ua.kharkiv.syvolotskyi.service.ServiceService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/create-service")
public class CreateServiceServlet extends HttpServlet {
    private ServiceService serviceService;

    @Override
    public void init(ServletConfig config) {
        serviceService = (ServiceService) config.getServletContext().getAttribute(ServiceService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.CREATE_SERVICE_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (serviceService != null) {
            serviceService.save(ConverterUtils.convertRequestToService(request));
        }
        response.sendRedirect("/admin/admin-home");
    }
}

