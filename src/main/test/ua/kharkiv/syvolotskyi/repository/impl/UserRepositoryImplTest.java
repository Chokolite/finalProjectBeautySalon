package ua.kharkiv.syvolotskyi.repository.impl;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryImplTest {
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Connection connection;
    private UserRepositoryImpl userRepository;

    @Before
    public void setUp() {
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        connection = mock(Connection.class);
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void getUserByEmailAndPassword() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(userRepository.getUserByEmailAndPassword(connection, "email", "password"));
    }

    @Test
    public void existsByIdAndEmail() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        assertFalse(userRepository.existsByIdAndEmail(connection, 1L, "email"));
    }
    @Test
    public void existsByEmail() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        assertFalse(userRepository.existsByEmail(connection, "email"));
    }

    @Test
    public void isEnabledById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        assertFalse(userRepository.isEnabledById(connection, anyLong()));
    }
}