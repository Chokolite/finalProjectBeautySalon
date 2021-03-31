package ua.kharkiv.syvolotskyi.transaction;

@FunctionalInterface
public interface TransactionManager {
    <U> U execute(TransactionAction<U> transactionAction);
}
