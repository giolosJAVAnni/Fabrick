package com.fabrick.conto.rest.to.api.service;

import com.fabrick.conto.rest.to.api.client.ClientApi;
import com.fabrick.conto.rest.to.api.model.balance.BalanceApiResponse;
import com.fabrick.conto.rest.to.api.model.transactions.TransactionsApiResponse;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionHistoryModelTable;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionHistoryRepository;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionModelTable;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionRepository;
import com.fabrick.conto.rest.to.api.model.transfer.BonificoApiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientApi clientApi;

    @Value("${api.url}")
    String apiUrl;

    // Repository: Save and Historicize Data Transactions
    @Autowired
    TransactionRepository repository;
    @Autowired
    TransactionHistoryRepository repositoryHistory;

    private static final Logger log = LoggerFactory.getLogger(OperationsServiceImpl.class);


    @Override
    public ResponseEntity getBalanceById(Long accountId) {
        ResponseEntity resp;
        try{
            HttpEntity<String> entity = new HttpEntity<>("body", clientApi.getHeaders());
            resp = restTemplate.exchange(
                apiUrl + "/accounts/" + accountId + "/balance",
                HttpMethod.GET, entity, BalanceApiResponse.class);
                log.info("[OperationsServiceImpl] - getBalanceById() - StatusCode: {}", resp.getStatusCode());
        } catch (Exception ex) {
            // Manage response...
            String msg = ex.getMessage();
            msg = msg.substring(msg.indexOf("[") + 1 , msg.length() -1).replaceAll("\n\"","\"");
            ResponseEntity response = ResponseEntity.badRequest().body(msg);
            log.error("[OperationsServiceImpl] - getBalanceById - exception - response:\n{}", response.getBody());
            resp = response;
        }
        return resp;
    }


    @Override
    public ResponseEntity getTransactionsByAccountId(Long accountId, String fromAccountingDate, String toAccountingDate) {

        log.info("[OperationsServiceImpl] - getTransactionsByAccountId - accountId: {} - fromAccountingDate: {} - toAccountingDate: {}", accountId, fromAccountingDate, toAccountingDate);
        ResponseEntity resp;
        try{
            HttpEntity<String> entity = new HttpEntity<>("body", clientApi.getHeaders());
            resp = restTemplate.exchange(apiUrl + "/accounts/" + accountId +
                            "/transactions?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate="+toAccountingDate,
                    HttpMethod.GET,
                    entity,
                    TransactionsApiResponse.class
            );

            log.info("[OperationsServiceImpl] - getTransactionsByAccountId() - StatusCode: {}", resp.getStatusCode());


            // ******************** Manage Data ************************
            // Save Data
            saveData(resp, accountId);

            // Historicize Data
            historicizeData(accountId, "2019-10-11");
            // *********************************************************

       } catch (Exception ex) {
        // Manage response...
        String msg = ex.getMessage();
        msg = msg.substring(msg.indexOf("[") + 1 , msg.length() -1).replaceAll("\n\"","\"");
        ResponseEntity response = ResponseEntity.badRequest().body(msg);
        log.error("[OperationsServiceImpl] - getTransactionsByAccountId - exception - response:\n{}", response.getBody());
        resp = response;
    }
        return resp;
    }

    @Override
    public ResponseEntity moneyTransfer(Long accountId, BonificoApiRequest requestPost) {

        log.info("[OperationsServiceImpl] - moneyTransfer - accountId: {} - requestPost: {}", accountId, requestPost);
        ResponseEntity resp;
        try {

            HttpEntity<String> entity = new HttpEntity<String>(requestPost.jsonToString(), clientApi.getHeaders());
            resp = restTemplate.exchange(apiUrl + "/accounts/" + accountId + "/payments/money-transfers",
                                                    HttpMethod.POST,entity,Object.class);
            log.info("[OperationsServiceImpl] - moneyTransfer - response call: {}",resp);
        } catch (Exception ex) {
            // Manage response for test case...
            String msg = ex.getMessage();
            msg = msg.substring(msg.indexOf("[") + 1 , msg.length() -1).replaceAll("\n\"","\"");
            ResponseEntity response = ResponseEntity.badRequest().body(msg);
            log.error("[OperationsServiceImpl] - moneyTransfer - exception - response:\n{}", response.getBody());
            resp = response;
        }
        return resp;
    }



    @Transactional
    public void saveData(ResponseEntity<String> response, Long accountId){
        // *********************** save transaction init ***********************
        try {
            ObjectMapper mapper = new ObjectMapper();
            String respStr = mapper.writeValueAsString(response.getBody());
            //log.info("respStr -------->{}", respStr);

            JSONObject myObject = new JSONObject(respStr);
            JSONObject payload = myObject.getJSONObject("payload");
            //log.info("payload -------->{}", payload);
            JSONArray list = payload.getJSONArray("list");
            //log.info("list -------->{}", list);

            Object objAppo;
            List<TransactionModelTable> allData = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                objAppo = list.getJSONObject(i);
                String accountingDate = ((JSONObject) objAppo).get("accountingDate").toString();
                //log.info("accountingDate--------------------->{}", accountingDate);
                allData.add(new TransactionModelTable(accountId, ((JSONObject) objAppo).toString(), accountingDate));
            }
            repository.saveAll(allData);
            log.info("Count repository (Before HISTORICIZATION)--------------------->{}", repository.count());

            List<TransactionModelTable> listFromDb = repository.findByAccountId(accountId);
            log.info("dal DB --------------------->{}", listFromDb);

            // *********************** save transaction end ***********************

        }catch(Exception e){
            log.error("[saveData] - error: {}", e.getMessage());
        }
    }


    // *********************** historicization data ************************
    @Transactional
    public void historicizeData(Long accountId, String accountingDateBefore){

        try {
            List<TransactionModelTable> listToBeHistory = repository.findAllByAccountingDateBefore(accountingDateBefore);
            log.info("dal DB listHistory--------------------->{}", listToBeHistory);

            List<TransactionHistoryModelTable> listIntoHistory = new ArrayList<>();
            TransactionHistoryModelTable appoData;
            for(TransactionModelTable appo: listToBeHistory){
                listIntoHistory.add(new TransactionHistoryModelTable(accountId, appo.getData(), appo.getAccountingDate()));
            }

            repositoryHistory.saveAll(listIntoHistory);
            log.info("from DB History--------------------->{}", repositoryHistory.findByAccountId(accountId));

            repository.deleteAll(listToBeHistory);
            log.info("from DB after DELETE--------------------->{}", repository.findByAccountId(accountId));


            log.info("Count repository (After DELETE)--------------------->{}", repository.count());
            log.info("Count repositoryHistory--------------------->{}", repositoryHistory.count());

        }catch(Exception e){
            log.error("[historicizeData] - error: {}", e.getMessage());
        }
    }

}
