package com.fabrick.conto;

import com.fabrick.conto.rest.to.api.model.account.AccountsApiResponse;
import com.fabrick.conto.rest.to.api.model.balance.BalanceApiResponse;
import com.fabrick.conto.rest.to.api.model.transactions.TransactionData;
import com.fabrick.conto.rest.to.api.model.transactions.TransactionsApiResponse;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionHistoryModelTable;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionModelTable;
import com.fabrick.conto.rest.to.api.model.transfer.*;
import com.fabrick.conto.rest.to.api.utility.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class TestApplication {

    @Autowired
    Utils utils;

    private static final Logger log = LoggerFactory.getLogger(OperazioniContoTests.class);

    @Test
     void checkApplicationCodCoverage() {
        log.info("checkApplicationStart()...");
        OperazioniContoApplication.main(new String[0]);

        AccountsApiResponse.ErrorCall accErrorCall = new AccountsApiResponse.ErrorCall();
        accErrorCall.setDescription("");
        accErrorCall.setCode("");
        accErrorCall.setParams("");
        accErrorCall.getDescription();
        accErrorCall.getCode();
        accErrorCall.getParams();
        accErrorCall.toString();

        TransactionsApiResponse.ErrorCall errorCall = new TransactionsApiResponse.ErrorCall();
        errorCall.setCode("");
        errorCall.setDescription("");
        errorCall.setParams("");
        errorCall.toString();

        TransactionData.TypeTransaction transactionData = new TransactionData.TypeTransaction("", "");
        transactionData.setEnumeration("");
        transactionData.setValue("");


       BalanceApiResponse.ErrorCall errorCall1 = new BalanceApiResponse.ErrorCall( );
       errorCall1.setCode("");
       errorCall1.setDescription("");
       errorCall1.setParams("");

       errorCall1.getCode();
       errorCall1.getDescription();
       errorCall1.getParams();
       errorCall1.toString();

       CreditorAddress creditorAddress = new CreditorAddress();
       creditorAddress.setAddress("");
       creditorAddress.setCountryCode("");
       creditorAddress.setCity("");

       TaxReliefData taxReliefData = new TaxReliefData();
        taxReliefData.setTaxReliefId("");
        taxReliefData.setBeneficiaryType("");
        taxReliefData.setCreditorFiscalCode("");
        taxReliefData.setCondoUpgrade(true);

        LegalPersonBeneficiary legalPersonBeneficiary = new LegalPersonBeneficiary();
        legalPersonBeneficiary.setFiscalCode("");
        legalPersonBeneficiary.setLegalRepresentativeFiscalCode("");

        TransactionModelTable transactionModelTable = new TransactionModelTable();
        transactionModelTable.setId(3415613L);

        TransactionHistoryModelTable transactionHistoryModelTable = new TransactionHistoryModelTable();
        transactionHistoryModelTable.setId(343653463L);

        BonificoApiRequest bonificoApiRequest = new BonificoApiRequest();
        bonificoApiRequest.setUri("");
        bonificoApiRequest.setFeeAccountId("");
        bonificoApiRequest.setFeeType("");
        bonificoApiRequest.setUrgent(true);
        bonificoApiRequest.setInstant(true);
        bonificoApiRequest.jsonToString();

        NaturalPersonBeneficiary naturalPersonBeneficiary = new NaturalPersonBeneficiary();
        naturalPersonBeneficiary.setFiscalCode1("");

        utils.getCurrentLocalDate();
        utils.getCurrentLocalDateMinusMonths(10L);
    }
}
