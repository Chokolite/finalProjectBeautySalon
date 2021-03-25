package ua.kharkiv.syvolotskyi.repository.impl;

import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.repository.ServiceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepositoryImpl implements ServiceRepository {
    private static final String SELECT_SERVICE_BY_ID = "select * from service where id=?";
    private static final String SELECT_ALL = "select * from service";
    private static final String INSERT_SERVICE = "insert into service (serviceName, serviceDuration, servicePrice) VALUES(?,?,?)";
    private static final String DELETE_SERVICE = "delete from service where id=?";

    @Override
    public Service getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToService(resultSet) : null;
    }

    @Override
    public List<Service> getAll(Connection connection) throws SQLException {
        List<Service> services = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        while (resultSet.next()) {
            services.add(convertResultSetToService(resultSet));
        }
        return services;
    }

    @Override
    public Long save(Connection connection, Service service) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_SERVICE, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(service, statement);
        statement.executeUpdate();
        return getGenerateId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_SERVICE);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    private Service convertResultSetToService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("id"));
        service.setName(resultSet.getString("serviceName"));
        service.setServiceDuration(resultSet.getString("serviceDuration"));
        service.setServicePrice(resultSet.getLong("servicePrice"));

        return service;
    }

    private void setAttributeForSave(Service service, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, service.getName());
        statement.setString(count++, service.getServiceDuration());
        statement.setLong(count, service.getServicePrice());
    }

    private Long getGenerateId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }
}
