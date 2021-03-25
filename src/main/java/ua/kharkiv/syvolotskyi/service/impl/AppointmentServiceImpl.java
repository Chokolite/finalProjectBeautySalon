package ua.kharkiv.syvolotskyi.service.impl;

import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.repository.AppointmentRepository;
import ua.kharkiv.syvolotskyi.service.AppointmentService;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private TransactionManager transactionManager;
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(TransactionManager transactionManager, AppointmentRepository appointmentRepository) {
        this.transactionManager = transactionManager;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAll() {
        return transactionManager.execute(c -> appointmentRepository.getAll(c));
    }

    @Override
    public Long save(Appointment appointment) {
        return transactionManager.execute(c -> appointmentRepository.save(c, appointment));
    }

    @Override
    public void saveReview(Long appointmentId, Long reviewId) {
        transactionManager.execute(c -> {
            appointmentRepository.saveReview(c, appointmentId, reviewId);
            return null;
        });
    }

    @Override
    public Appointment getById(Long id) {
        return transactionManager.execute(c -> appointmentRepository.getById(c, id));
    }

    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            appointmentRepository.delete(c, id);
            return null;
        });
    }

    @Override
    public void update(Appointment appointment) {
        transactionManager.execute(c -> {
            appointmentRepository.update(c, appointment);
            return null;
        });
    }
}
