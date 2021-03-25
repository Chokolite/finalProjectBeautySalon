package ua.kharkiv.syvolotskyi.transaction;

public interface TransactionManager {

    <U> U execute(TransactionAction<U> transactionAction);

}
