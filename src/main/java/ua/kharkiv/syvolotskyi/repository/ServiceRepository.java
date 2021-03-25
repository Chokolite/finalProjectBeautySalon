package ua.kharkiv.syvolotskyi.repository;

import ua.kharkiv.syvolotskyi.entity.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ServiceRepository {
    Service getById(Connection connection, Long id) throws SQLException;

    List<Service> getAll(Connection connection) throws SQLException;

    Long save(Connection connection, Service service) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;
}
