package ua.kharkiv.syvolotskyi.repository.impl;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.Appointment;
import ua.kharkiv.syvolotskyi.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceRepositoryImplTest {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ServiceRepositoryImpl serviceRepository;

    @Before
    public void setUp() {
        connection = mock(Connection.class);
        statement = mock(Statement.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        serviceRepository = new ServiceRepositoryImpl();
    }

    @Test
    public void getById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(serviceRepository.getById(connection, 1L));
    }

    @Test
    public void getAll() throws SQLException {
        List<Service> services = new ArrayList<>();
        when(connection.createStatement()).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertEquals(services, serviceRepository.getAll(connection));
    }
}