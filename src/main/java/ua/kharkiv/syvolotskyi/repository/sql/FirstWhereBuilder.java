package ua.kharkiv.syvolotskyi.repository.sql;

public interface FirstWhereBuilder {

    WhereBuilder equal(String fieldName, Object value);

    WhereBuilder like(String fieldName, String value);

    WhereBuilder and();

}
