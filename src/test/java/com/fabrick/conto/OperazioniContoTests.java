package com.fabrick.conto;

import com.fabrick.conto.rest.to.api.controller.OperationsCcController;
import com.fabrick.conto.rest.to.api.model.account.AccountsApiResponse;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionHistoryModelTable;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionHistoryRepository;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionModelTable;
import com.fabrick.conto.rest.to.api.model.transactions.savedata.TransactionRepository;
import com.fabrick.conto.rest.to.api.model.transfer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableJpaRepositories
@RunWith(SpringJUnit4ClassRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OperazioniContoTests {

	@Autowired
	private OperationsCcController ccController;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	TransactionRepository repository;

	@Autowired
	TransactionHistoryRepository repositoryHistory;

	private static final Logger log = LoggerFactory.getLogger(OperazioniContoTests.class);

	// {accountId} for tests on 'Sandbox'...
	private static Long ACCOUNT_ID = 14537780L;
	private static Long ACCOUNT_ID_KO = 145377801L;


	@Test
	@Order(1)
	@DisplayName("SaldoApiService")
	void testCallSaldoController() {

		try {
			ResponseEntity<String> response = ccController.getBalanceById(ACCOUNT_ID);
			log.info("[testCallBonificoController] - SaldoApiService() - response:\n{}", response);

			Assert.assertTrue(response.getStatusCode().toString().equals("200 OK"));

		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCallBonificoController] - Exception - test-SaldoApiService() - response:\n{}", response.getBody());
		}
	}

	@Test
	@Order(2)
	@DisplayName("SaldoApiService-KO")
	void testCallSaldoController_KO() {

		try {
			ResponseEntity<String> response = ccController.getBalanceById(ACCOUNT_ID_KO);
			log.info("[testCallBonificoController] - test-SaldoApiService() - response:\n{}", response);
		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCallBonificoController] - Exception - test-SaldoApiService() - response:\n{}", response.getBody());

			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));
		}
	}

	@Test
	@Order(3)
	@DisplayName("LetturaTransazioni")
	void testCalltransazioniController() {

		try {
			ResponseEntity<String> response = ccController.readTransactionsByAccountId(ACCOUNT_ID, "2019-01-10", "2019-12-01");
			log.info("[testCalltransazioniController] - test - testCalltransazioniController() - response:\n{}", response.getBody());

			Assert.assertTrue(response.getStatusCode().toString().equals("200 OK"));
		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCalltransazioniController] - Exception - test-testCalltransazioniController() - response:\n{}", response.getBody());
		}
	}
	@Test
	@Order(4)
	@DisplayName("LetturaTransazioni-KO")
	void testCalltransazioniControllerKO() {

		try {
			ResponseEntity<String> response = ccController.readTransactionsByAccountId(ACCOUNT_ID_KO, "2019-01-10", "2019-12-01");
			log.info("[testCalltransazioniControllerKO] - test - testCalltransazioniController() - response:\n{}", response.getBody());
		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCalltransazioniController] - Exception - test-testCalltransazioniController() - response:\n{}", response.getBody());

			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));
		}
	}

	@Test
	@Order(5)
	@DisplayName("BonificoApiService")
	void testCallBonificoController() {

		try {
			// I manage requestPost for test case "code": "API000"
			BonificoApiRequest reqTest = new BonificoApiRequest();
			CreditorData creditorData = new CreditorData();
			creditorData.setName("GIANNI");

			CreditorAccount creditorAccount = new CreditorAccount();
			creditorAccount.setAccountCode("IT23A0336844430152923804660");
			creditorAccount.setBicCode("SELBIT2BXXX");
			creditorData.setAccount(creditorAccount);
			creditorData.setAddress(new CreditorAddress());

			reqTest.setCreditor(creditorData);
			try
			{
				reqTest.setExecutionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-05"));
			} catch (ParseException e) {
				log.error("Error parsing date: {}", e.getMessage());
			}

			reqTest.setDescription("TEST MONEY TRANSFER");
			reqTest.setAmount(100F);
			reqTest.setCurrency("EUR");

			reqTest.setTaxRelief(new TaxReliefData());
			reqTest.getTaxRelief().setLegalPersonBeneficiary(new LegalPersonBeneficiary());
			reqTest.getTaxRelief().setNaturalPersonBeneficiary(new NaturalPersonBeneficiary());

			ResponseEntity<String> response = ccController.postMoneyTransfer(ACCOUNT_ID, reqTest);

			log.info("[testCallBonificoController] - response:\n{}", response);
			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));

		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCallBonificoController] - Exception - response:\n{}", response.getBody());
			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));
		}
	}


	@Test
	@Order(6)
	@DisplayName("BonificoApiService-KO")
	void testCallBonificoControllerKO() {

		try {
			// I manage requestPost for KO-test case
			BonificoApiRequest reqTest = new BonificoApiRequest();
			CreditorData creditorData = new CreditorData();
			//creditorData.setName("GIANNI");

			CreditorAccount creditorAccount = new CreditorAccount();
			//creditorAccount.setAccountCode("IT23A0336844430152923804660");
			//creditorAccount.setBicCode("SELBIT2BXXX");
			creditorData.setAccount(creditorAccount);
			creditorData.setAddress(new CreditorAddress());

			reqTest.setCreditor(creditorData);
			try
			{
				reqTest.setExecutionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-05"));
			} catch (ParseException e) {
				log.error("Error parsing date: {}", e.getMessage());
			}

			reqTest.setDescription("TEST MONEY TRANSFER");
			reqTest.setAmount(100F);
			reqTest.setCurrency("EUR");

			reqTest.setTaxRelief(new TaxReliefData());
			reqTest.getTaxRelief().setLegalPersonBeneficiary(new LegalPersonBeneficiary());
			reqTest.getTaxRelief().setNaturalPersonBeneficiary(new NaturalPersonBeneficiary());

			ResponseEntity<String> response = ccController.postMoneyTransfer(ACCOUNT_ID_KO, reqTest);

			log.info("[testCallBonificoController] - response:\n{}", response);
			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));

		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testCallBonificoController] - Exception - response:\n{}", response.getBody());
			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));
		}
	}


	// ******** my personal api test **********

	@Test
	@Order(7)
	@DisplayName("getAccountById - for my test")
	void getAccountById() {
		try {
			ResponseEntity<String> response = ccController.getAccountById(ACCOUNT_ID);
			log.info("[testgetAccountById] - test - testgetAccountById() - response:\n{}", response.getBody());
			Assert.assertTrue(response.getStatusCode().toString().equals("200 OK"));
		} catch (Exception ex) {
			ResponseEntity response = ResponseEntity.badRequest().body(ex.toString());
			log.error("[testgetAccountById] - Exception - getAccountById() - response:\n{}", response.getBody());
			Assert.assertTrue(response.getStatusCode().toString().equals("400 BAD_REQUEST"));
		}

	}
}