package ua.kharkiv.syvolotskyi.repository;

import ua.kharkiv.syvolotskyi.entity.Appointment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentRepository {

    List<Appointment> getAll(Connection connection) throws SQLException;

    Long save(Connection connection, Appointment appointment) throws SQLException;

    void saveReview(Connection connection, Long appointmentId, Long reviewId) throws SQLException;

    Appointment getById(Connection connection, Long id) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void update(Connection connection, Appointment appointment) throws SQLException;
}
