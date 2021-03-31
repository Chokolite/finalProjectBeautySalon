package ua.kharkiv.syvolotskyi.repository.impl;

import org.junit.Test;
import ua.kharkiv.syvolotskyi.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRepositoryImplTest {

    @Test
    public void getAll() throws SQLException {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);
        Connection connection = mock(Connection.class);
        ReviewRepositoryImpl reviewRepository = new ReviewRepositoryImpl();
        List<Review> reviewList = new ArrayList<>();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertEquals(reviewList, reviewRepository.getAll(connection));
    }
}