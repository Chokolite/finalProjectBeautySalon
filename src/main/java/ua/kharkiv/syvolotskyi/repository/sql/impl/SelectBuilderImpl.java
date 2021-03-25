package ua.kharkiv.syvolotskyi.repository.sql.impl;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.repository.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectBuilderImpl implements SelectBuilder, WhereBuilder {
    private static final Logger LOG = Logger.getLogger(SelectBuilderImpl.class);

    private StringBuilder query = new StringBuilder();
    private List<Object> args = new ArrayList<>();

    public SelectBuilderImpl(String selectQuery) {
        query.append(selectQuery);
    }

    @Override
    public FirstWhereBuilder where() {
        query.append(" WHERE ");
        return this;
    }

    @Override
    public WhereBuilder equal(String fieldName, Object value) {
        query.append(fieldName).append(" = ").append("? ");
        args.add(value);
        return this;
    }

    @Override
    public WhereBuilder and() {
        query.append(" AND ");
        return this;
    }
    @Override
    public WhereBuilder like(String fieldName, String value) {
        query.append(fieldName).append(" like(?) ");
        args.add('%' + value + '%');
        return this;
    }

    @Override
    public PreparedStatementBuilder limit(int offset, int size) {
        query.append(" LIMIT ").append(offset).append(',').append(size);
        return this;
    }

    @Override
    public OrderBuilder order(String fieldName, boolean ascending) {
        query.append(" ORDER BY ").append(fieldName).append(' ').append(ascending ? "ASC" : "DESC");
        return this;
    }

    @Override
    public PreparedStatement buildPrepareStatement(Connection connection) throws SQLException {
        String sql = query.toString();
        LOG.debug("LOG SelectBuilder: sql query: " + sql);
        PreparedStatement statement = connection.prepareStatement(sql);

        int i = 1;
        for (Object arg : args) {
            statement.setObject(i++, arg);
        }

        return statement;
    }
}


