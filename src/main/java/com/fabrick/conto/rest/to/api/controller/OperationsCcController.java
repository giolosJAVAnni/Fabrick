package com.fabrick.conto.rest.to.api.controller;

import com.fabrick.conto.rest.to.api.client.ClientApi;
import com.fabrick.conto.rest.to.api.model.account.AccountsApiResponse;
import com.fabrick.conto.rest.to.api.model.transfer.BonificoApiRequest;
import com.fabrick.conto.rest.to.api.service.OperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class OperationsCcController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientApi clientApi;

    @Value("${api.url}")
    String apiUrl;

    @Autowired
    OperationsService operationsService;

    private static final Logger log = LoggerFactory.getLogger(OperationsCcController.class);


    @GetMapping(value = "/accounts/{accountId}/balance", produces = "application/json")
    public @ResponseBody ResponseEntity<String> getBalanceById(
            @PathVariable("accountId") Long accountId){

        log.info("[OperationsCcController] - [getBalanceById] - accountId: {}", accountId);
        // check input
        // not necessary - the Fabrick's service manage all cases, so I wrap all...
        ResponseEntity resp = operationsService.getBalanceById(accountId);
        return new ResponseEntity(resp.getBody(), resp.getStatusCode());
    }


    @GetMapping(value = "accounts/{accountId}/transactions", produces = "application/json")
    public @ResponseBody ResponseEntity<String> readTransactionsByAccountId(
            @PathVariable(name = "accountId") Long accountId, @RequestParam(name = "fromAccountingDate") String fromAccountingDate, @RequestParam(name="toAccountingDate") String toAccountingDate){

        log.info("[OperationsCcController] - readTransactionsByAccountId() - accountId: {} - fromAccountingDate: {} - toAccountingDate: {}", accountId, fromAccountingDate, toAccountingDate);
        // check input
        // not necessary - the Fabrick's service manage all cases, so I wrap all...

        ResponseEntity resp = operationsService.getTransactionsByAccountId(accountId, fromAccountingDate, toAccountingDate);
        return new ResponseEntity(resp.getBody(), resp.getStatusCode());
    }


    @PostMapping(value = "accounts/{accountId}/money-transfers", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<String> postMoneyTransfer(
            @PathVariable(name = "accountId") Long accountId, @RequestBody BonificoApiRequest requestPost){

        log.info("[OperationsCcController] - postMoneyTransfer() - accountId: {} - requestPost: {}", accountId, requestPost);
        // check input
        // not necessary - the Fabrick's service manage all cases, so I wrap all...
         ResponseEntity resp = operationsService.moneyTransfer(accountId, requestPost);

        return new ResponseEntity(resp.getBody(), resp.getStatusCode());
    }






    // **************************************** for my api test *************************************

    @GetMapping(value = "/accounts/{accountId}", produces = "application/json")
    public @ResponseBody ResponseEntity<String> getAccountById(
            @PathVariable("accountId") Long accountId){

        log.info("[getAccountById] - accountId: {}", accountId);

        // check input
        // not necessary - the Fabrick's service manage all cases

        HttpEntity<String> entity = new HttpEntity<>("body", clientApi.getHeaders());
        ResponseEntity resp = restTemplate.exchange(
                apiUrl + "/accounts?acountId=" + accountId,
                HttpMethod.GET, entity, AccountsApiResponse.class);

        log.info("[getAccountById] - StatusCode: {}", resp.getStatusCode());
        return new ResponseEntity(resp.getBody(), resp.getStatusCode());
    }

// **************************************** test end *************************************
}