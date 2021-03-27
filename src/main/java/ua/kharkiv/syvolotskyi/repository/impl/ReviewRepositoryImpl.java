package ua.kharkiv.syvolotskyi.repository.impl;

import ua.kharkiv.syvolotskyi.entity.Review;
import ua.kharkiv.syvolotskyi.repository.ReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {
    private static final String SELECT_ALL_REVIEW = "select * from review";
    private static final String INSERT_REVIEW = "insert into review (comment, rating) values(?, ?)";

    @Override
    public List<Review> getAll(Connection connection) throws SQLException {
        List<Review> reviewList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_REVIEW);
        while (resultSet.next()) {
            reviewList.add(convertResultsetToReview(resultSet));
        }
        return reviewList;
    }

    @Override
    public Long save(Connection connection, Review review) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSaveReview(review, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    private Review convertResultsetToReview(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getLong("id"));
        review.setReview(resultSet.getString("comment"));
        review.setRating(resultSet.getLong("rating"));
        return review;
    }

    private void setAttributeForSaveReview(Review review, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, review.getReview());
        statement.setLong(count, review.getRating());
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }
}
