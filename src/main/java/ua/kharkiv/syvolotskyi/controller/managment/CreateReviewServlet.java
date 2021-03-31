package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.ReviewService;
import ua.kharkiv.syvolotskyi.utils.ConverterUtils;
import ua.kharkiv.syvolotskyi.utils.JspConstants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/client/create-review"})
public class CreateReviewServlet extends HttpServlet {
    private ReviewService reviewService;
    private AppointmentService appointmentService;

    @Override
    public void init(ServletConfig config) {
        reviewService = (ReviewService) config.getServletContext().getAttribute(ReviewService.class.toString());
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.CREATE_REVIEW_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (reviewService != null && appointmentService != null) {
            Long reviewId = reviewService.save(ConverterUtils.convertRequestToReview(request));
            appointmentService.saveReview(Long.valueOf(request.getParameter("appointmentId")), reviewId);
        }
        response.sendRedirect("/client/client-home");
    }
}
