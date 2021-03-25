package ua.kharkiv.syvolotskyi.transaction.impl;


import ua.kharkiv.syvolotskyi.exception.RepositoryException;
import ua.kharkiv.syvolotskyi.transaction.TransactionAction;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {

    private DataSource dataSource;

    public TransactionManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <U> U execute(TransactionAction<U> transactionAction) {
        U result;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            result = transactionAction.action(connection);
            connection.commit();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return result;
    }
}
