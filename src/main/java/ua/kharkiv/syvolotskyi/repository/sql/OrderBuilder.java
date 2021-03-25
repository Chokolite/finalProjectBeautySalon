package ua.kharkiv.syvolotskyi.repository.sql;

public interface OrderBuilder extends LimitBuilder, PreparedStatementBuilder {

    LimitBuilder order(String fieldName, boolean ascending);
}
