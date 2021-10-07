package com.fabrick.conto.rest.to.api.model.transactions.savedata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryModelTable, Long> {
    List<TransactionHistoryModelTable> findByAccountId(Long accountId);
    List<TransactionHistoryModelTable> findAllByAccountingDateAfter(String accountingDate);
    List<TransactionHistoryModelTable> findAllByAccountingDateIsBetween(String accountingDateFrom, String accountingDateTo);
}
