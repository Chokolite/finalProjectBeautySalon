package ua.kharkiv.syvolotskyi.repository.impl;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AppointmentRepositoryImplTest {
    private AppointmentRepositoryImpl appointmentRepository;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Appointment appointment;

    @Before
    public void setUp() {
        appointmentRepository = new AppointmentRepositoryImpl();
        appointment = mock(Appointment.class);
        connection = mock(Connection.class);
        statement = mock(Statement.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void getAll() throws SQLException {
        List<Appointment> appointmentList = new ArrayList<>();
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertEquals(appointmentList, appointmentRepository.getAll(connection));
    }

    @Test
    public void getUsersEmailsForSheduler() throws SQLException {
        List<String> email = new ArrayList<>();
        email.add("test@mail.com");
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("email")).thenReturn("test@mail.com");
        assertEquals(email, appointmentRepository.getUsersEmailsForSheduler(connection));
    }

    @Test
    public void getById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(appointmentRepository.getById(connection, 1L));
    }

}