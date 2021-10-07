package com.fabrick.conto.rest.to.api.service;

import com.fabrick.conto.rest.to.api.model.transfer.BonificoApiRequest;
import org.springframework.http.ResponseEntity;

public interface OperationsService {

    ResponseEntity getBalanceById(Long accountId);
    ResponseEntity getTransactionsByAccountId(Long accountId, String fromAccountingDate, String toAccountingDate);
    ResponseEntity moneyTransfer(Long accountId, BonificoApiRequest requestPost);

}
