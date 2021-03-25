package ua.kharkiv.syvolotskyi.service;

import ua.kharkiv.syvolotskyi.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();

    Long save(Appointment appointment);

    Appointment getById(Long id);

    void delete(Long id);

    void update(Appointment appointment);

    void saveReview(Long appointmentId, Long reviewId);
}
