package ua.kharkiv.syvolotskyi.repository.impl;

import ua.kharkiv.syvolotskyi.entity.*;
import ua.kharkiv.syvolotskyi.repository.AppointmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    private static final String SELECT_ALL = "select distinct a.id a_id, a.customerId  a_customerId," +
            " a.catalogId   a_catalogId, a.appointmentDate a_dateTime, " +
            " a.status a_status, a.reviewId a_reviewId, " +
            " c.serviceId c_serviceId, " +
            " u.name        u_name," +
            " um.name       um_name," +
            " um.id um_id," +
            " s.serviceName s_serviceName" +
            " from appointment a," +
            "     catalog c," +
            "     service s," +
            "     users u," +
            "     users um" +
            " where c.serviceId = s.id" +
            "  and c.masterId = um.id" +
            "  and a.customerId = u.id" +
            " and c.id = a.catalogId";
    private static final String SELECT_APPOINTMENT_BY_ID = SELECT_ALL + " and a.id=?";
    private static final String INSERT_APPOINTMENT = "insert into appointment (customerId, catalogId, appointmentDate, status) values(?, ?, ?, ?)";
    private static final String UPDATE_APPOINTMENT_REVIEW = "update appointment set reviewId=? where id=?";
    private static final String DELETE_APPOINTMENT = "delete from appointment where id=?";
    private static final String UPDATE_APPOINTMENT = "update appointment set customerId=?, catalogId=?, appointmentDate=?, " +
            "status=? where id=?";

    @Override
    public List<Appointment> getAll(Connection connection) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        while (resultSet.next()) {
            appointments.add(convertResultSetToAppointment(resultSet));
        }
        return appointments;
    }

    @Override
    public Appointment getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToAppointment(resultSet) : null;
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public Long save(Connection connection, Appointment appointment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_APPOINTMENT, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(appointment, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void saveReview(Connection connection, Long appointmentId, Long reviewId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_REVIEW);
        setAttributeForSaveReview(appointmentId, reviewId, statement);
        statement.executeUpdate();
    }

    @Override
    public void update(Connection connection, Appointment appointment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT);
        setAttributeForUpdate(appointment, statement);
        statement.executeUpdate();
    }

    private Appointment convertResultSetToAppointment(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment();
        Catalog catalog = new Catalog();
        Master master = new Master();
        Client client = new Client();
        Service service = new Service();

        service.setName(resultSet.getString("s_serviceName"));
        service.setId(resultSet.getLong("c_serviceId"));

        client.setId(resultSet.getLong("a_customerId"));
        client.setName(resultSet.getString("u_name"));
        master.setId(resultSet.getLong("um_id"));

        master.setName(resultSet.getString("um_name"));

        catalog.setId(resultSet.getLong("a_catalogId"));
        catalog.setService(service);
        catalog.setMaster(master);

        appointment.setId(resultSet.getLong("a_id"));
        appointment.setClient(client);
        appointment.setCatalog(catalog);
        if(resultSet.getString("a_status") != null) {
            appointment.setStatus(Status.valueOf(resultSet.getString("a_status")));
        } else {
            appointment.setStatus(Status.OPEN);
        }
        appointment.setLocalDateTime(resultSet.getTimestamp("a_dateTime").toLocalDateTime());
        appointment.setReviewId(resultSet.getLong("a_reviewId"));
        return appointment;
    }

    private void setAttributeForSave(Appointment appointment, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setLong(count++, appointment.getClient().getId());
        statement.setLong(count++, appointment.getCatalog().getId());
        statement.setTimestamp(count++, Timestamp.valueOf(appointment.getLocalDateTime()));
        statement.setString(count, String.valueOf(appointment.getStatus()));
    }

    private void setAttributeForSaveReview(Long appointmentId, Long reviewId, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setLong(count++, reviewId);
        statement.setLong(count, appointmentId);
    }

    private void setAttributeForUpdate(Appointment appointment, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setLong(count++, appointment.getClient().getId());
        statement.setLong(count++, appointment.getCatalog().getId());
        statement.setTimestamp(count++, Timestamp.valueOf(appointment.getLocalDateTime()));
        statement.setString(count++, String.valueOf(appointment.getStatus()));
        statement.setLong(count, appointment.getId());
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }
}
