package ua.kharkiv.syvolotskyi.entity;

public class Master extends User {
    private Service service;
    private Appointment appointment;
    private Long rating;

    public Long getRating() {
        return rating;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
