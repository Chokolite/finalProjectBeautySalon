package ua.kharkiv.syvolotskyi.controller.managment;

import ua.kharkiv.syvolotskyi.controller.common.ConverterUtils;
import ua.kharkiv.syvolotskyi.controller.common.JspConstants;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.service.ReviewService;
import ua.kharkiv.syvolotskyi.service.UserService;

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
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        reviewService = (ReviewService) config.getServletContext().getAttribute(ReviewService.class.toString());
        appointmentService = (AppointmentService) config.getServletContext().getAttribute(AppointmentService.class.toString());
        userService = (UserService) config.getServletContext().getAttribute(UserService.class.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspConstants.CREATE_REVIEW_JSP).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reviewId = reviewService.save(ConverterUtils.convertRequestToReview(request));
        appointmentService.saveReview(Long.valueOf(request.getParameter("appointmentId")), reviewId);
        response.sendRedirect("/client/client-home");
    }
}
