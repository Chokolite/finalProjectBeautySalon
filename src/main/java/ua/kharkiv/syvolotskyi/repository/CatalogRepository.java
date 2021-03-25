package ua.kharkiv.syvolotskyi.repository;

import ua.kharkiv.syvolotskyi.entity.Catalog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CatalogRepository {
    List<Catalog> getAll(Connection connection, String name, String type, String orderBy, int offset, int size) throws SQLException;

    Catalog getById(Connection connection, Long id) throws SQLException;

    Long save(Connection connection, Catalog catalog) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;
}
