package ua.kharkiv.syvolotskyi.repository.impl;

import ua.kharkiv.syvolotskyi.entity.Role;
import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.repository.UserRepository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL = "select * from users";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "select * from users where email = ? and password = ?";
    private static final String INSERT_USER = "insert into users (name, password, email, role) values(?,?,?,?)";
    private static final String UPDATE_USER = "update users set name=?, password=?, email=? where id=?";
    private static final String UPDATE_ENABLED = "update users set enabled=? where id=?";
    private static final String DELETE_USER = "delete from uesers where id=?";
    private static final String EXIST_USER_BY_EMAIL = "select 1 from users where email=?";
    private static final String EXIST_USER_BY_ID_AND_EMAIL = "select 1 from users where id=? and email=?";
    private static final String EXISTS_USER_BY_ID_AND_ENABLED = "select 1 from users where id = ? and enabled = 1";

    @Override
    public User getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToUser(resultSet) : null;
    }

    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        while (resultSet.next()) {
            list.add(convertResultSetToUser(resultSet));
        }
        return list;
    }

    @Override
    public User getUserByEmailAndPassword(Connection connection, String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToUser(resultSet) : null;
    }

    @Override
    public Long save(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(user, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_USER);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public void update(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
        setAttributeForUpdate(user, statement);
        statement.executeUpdate();
    }

    @Override
    public boolean existsByIdAndEmail(Connection connection, Long id, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXIST_USER_BY_ID_AND_EMAIL);
        statement.setLong(1, id);
        statement.setString(2, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public boolean existsByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXIST_USER_BY_EMAIL);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public boolean isEnabledById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXISTS_USER_BY_ID_AND_ENABLED);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void updateEnabled(Connection connection, Long id, boolean b) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_ENABLED);
        statement.setBoolean(1, b);
        statement.setLong(2, id);
        statement.executeUpdate();
    }

    private User convertResultSetToUser(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setName(resultSet.getString("name"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));

        return user;
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private void setAttributeForSave(User user, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, user.getName());
        statement.setString(count++, user.getPassword());
        statement.setString(count++, user.getEmail());
        statement.setString(count, user.getRole().name());
    }

    private void setAttributeForUpdate(User user, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, user.getName());
        statement.setString(count++, user.getPassword());
        statement.setString(count, user.getEmail());
    }
}