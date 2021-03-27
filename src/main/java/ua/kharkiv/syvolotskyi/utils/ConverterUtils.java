package ua.kharkiv.syvolotskyi.utils;

import ua.kharkiv.syvolotskyi.entity.*;
import ua.kharkiv.syvolotskyi.exception.ValidationEnum;
import ua.kharkiv.syvolotskyi.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ConverterUtils {

    private ConverterUtils() {}

    public static Service convertRequestToService(HttpServletRequest request) {
        Service service = new Service();
        String name = request.getParameter("serviceName");
        String serviceDuration = request.getParameter("serviceDuration");
        if (request.getParameter("servicePrice") != null) {
            Long servicePrice = Long.valueOf(request.getParameter("servicePrice"));
            service.setServicePrice(servicePrice);
        }
        service.setName(name);
        service.setServiceDuration(serviceDuration);
        return service;
    }

    public static Catalog convertRequestToCatalog(HttpServletRequest request) {
        Catalog catalog = new Catalog();
        Service service = new Service();
        Master master = new Master();
        service.setId(Long.valueOf(request.getParameter("serviceId")));
        master.setId(Long.valueOf(request.getParameter("masterId")));
        catalog.setService(service);
        catalog.setMaster(master);
        return catalog;
    }

    public static Appointment convertRequestToAppointment(HttpServletRequest request) {
        String dateTime = request.getParameter("dateTime");
        Appointment appointment = new Appointment();
        Catalog catalog = new Catalog();
        Client client = new Client();

        catalog.setId(Long.valueOf(request.getParameter("catalogId")));
        client.setId(Long.valueOf(request.getParameter("clientId")));

        if(request.getParameter("appointmentId") != null){
            appointment.setId(Long.valueOf(request.getParameter("appointmentId")));
        }
        appointment.setCatalog(catalog);
        appointment.setClient(client);
        appointment.setLocalDateTime(LocalDateTime.parse(dateTime));
        if(request.getParameter("status") != null) {
            appointment.setStatus(Status.valueOf(request.getParameter("status")));
        } else {
            appointment.setStatus(Status.OPEN);
        }
        return appointment;
    }

    public static Client convertRequestToClient(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        if (!password.equals(rePassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPassword(PasswordEncoder.encode(password));
        client.setEnabled(true);
        client.setRole(Role.CLIENT);
        return client;
    }

    public static Master convertRequestToMaster(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        if (!password.equals(rePassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        Master master = new Master();
        master.setName(name);
        master.setEmail(email);
        master.setPassword(PasswordEncoder.encode(password));
        master.setEnabled(true);
        master.setRole(Role.MASTER);
        return master;
    }

    public static User convertRequestToAdmin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        if (!password.equals(rePassword)) {
            ValidationException.builder().put("passwordError", ValidationEnum.PASSWORDS_DO_NOT_MATCH).throwIfErrorExists();
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(PasswordEncoder.encode(password));
        user.setEnabled(true);
        user.setRole(Role.ADMIN);
        return user;
    }

    public static Review convertRequestToReview(HttpServletRequest request) {
        Review review = new Review();
        review.setReview(request.getParameter("review"));
        review.setRating(Long.valueOf(request.getParameter("rating")));
        return review;
    }
}
