package ua.kharkiv.syvolotskyi.repository;

import ua.kharkiv.syvolotskyi.entity.Review;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReviewRepository {
    List<Review> getAll(Connection connection) throws SQLException;

    Long save(Connection connection, Review review) throws SQLException;
}
