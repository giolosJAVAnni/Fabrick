# 'OperationsCC' to Fabrick's API

## Overview

**_OperationsCC_** is a Rest Controller that implements some operations that _use/consume_ the Fabrick API:
 * _Lettura saldo_
 * _Lista di transazioni_
 * _Bonifico_

Data storage is done in an H2 DB (for saving and historicization data)

## Environment Variables to set before running service in a centralized env
* API_URL => Fabrick's API environement endpoint
* API_KEY => Fabrick's key to use
* AUTH_SCHEMA => Fabrick's Authorization Schema

## Software Architecture
* Java 11/ maven
* Spring Boot/Web
* REST controller with management response in agreed JSON format
* mvn failsafe plugin for integration tests
* Logging
* Spring Data JPA
* Jacoco code coverage - report generated in target/site/jacoco

## Getting Started

### Prerequisites
* Any Jdk 11  installed on the local machine
* Maven

### Install and Run

``` manifest
mvn clean install
```

Initially run ``mvn install`` rather than ``mvn package`` or ``mvn install -DskipTests`` as this application uses sub maven modules.
(Note that maven install also runs integration tests)

### Local Tests (Postman)

``` manifest
mvn spring-boot:run
```
example: 
### Postman
* #### 'Saldo':
  * Method: GET
  * http://localhost:8080/accounts/14537780/balance
* #### 'Lista di transazioni':
    * Method: GET
    * http://localhost:8080/accounts/14537780/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01
* #### 'Bonifico':
    * Method: POST
    * http://localhost:8080/accounts/14537780/money-transfers
    * Body-row:
      ` {"creditor":{
        "name":"GIANNI",
        "account":{
        "accountCode":"IT23A0336844430152923804660",
        "bicCode":"SELBIT2BXXX"},
        "address":{
        "address":null,
        "city":null,
        "countryCode":null}
        },
        "executionDate":"2020-10-07",
        "uri":null,
        "description":"TEST MONEY TRANSFER",
        "amount":100.00,
        "currency":"EUR",
        "isUrgent":false,
        "isInstant":false,
        "feeType":null,
        "feeAccountId":null,
        "taxRelief":{
        "taxReliefId":null,
        "isCondoUpgrade":false,
        "creditorFiscalCode":null,
        "beneficiaryType":null,
        "naturalPersonBeneficiary":{
        "fiscalCode1":null},
        "legalPersonBeneficiary":{
        "fiscalCode":null,
        "legalRepresentativeFiscalCode":null}
        }
        }`
      

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.11/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.11/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
