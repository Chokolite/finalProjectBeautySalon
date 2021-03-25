package ua.kharkiv.syvolotskyi.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface WhereBuilder extends FirstWhereBuilder, OrderBuilder {
    PreparedStatement buildPrepareStatement(Connection connection) throws SQLException;
}
