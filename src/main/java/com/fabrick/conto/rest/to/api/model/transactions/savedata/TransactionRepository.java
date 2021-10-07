package com.fabrick.conto.rest.to.api.model.transactions.savedata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionModelTable, Long> {
    List<TransactionModelTable> findByAccountId(Long accountId);
    List<TransactionModelTable> findAllByAccountingDateAfter(String accountingDate);
    List<TransactionModelTable> findAllByAccountingDateBefore(String accountingDate);
}
