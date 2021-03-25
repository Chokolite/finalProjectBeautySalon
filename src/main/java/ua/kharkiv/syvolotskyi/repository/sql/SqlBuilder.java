package ua.kharkiv.syvolotskyi.repository.sql;

import ua.kharkiv.syvolotskyi.repository.sql.impl.SelectBuilderImpl;

public class SqlBuilder {

    public static SelectBuilder select(String selectQuery){
        return new SelectBuilderImpl(selectQuery);
    }

}