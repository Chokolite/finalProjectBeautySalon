package ua.kharkiv.syvolotskyi.repository.impl;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatalogRepositoryImplTest {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;
    private CatalogRepositoryImpl catalogRepository;
    @Before
    public void setUp() {
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        connection = mock(Connection.class);
        catalogRepository = new CatalogRepositoryImpl();
    }
    @Test
    public void getById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(catalogRepository.getById(connection, 1L));
    }

    @Test
    public void getCount() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        assertEquals(0L, (long) catalogRepository.getCount(connection, "masterName"));
    }
}