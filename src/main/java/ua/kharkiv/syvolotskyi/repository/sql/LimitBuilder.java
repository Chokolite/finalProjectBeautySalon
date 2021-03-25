package ua.kharkiv.syvolotskyi.repository.sql;

public interface LimitBuilder extends PreparedStatementBuilder {

    PreparedStatementBuilder limit(int offset, int size);

}