package ua.kharkiv.syvolotskyi.transaction.impl;

import org.junit.Assert;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.transaction.TransactionAction;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionManagerImplTest {

    @Test
    public void execute() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        TransactionAction transactionAction = mock(TransactionAction.class);
        TransactionManagerImpl transactionManager = new TransactionManagerImpl(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);
        Assert.assertEquals(null, transactionManager.execute(transactionAction));
    }
}