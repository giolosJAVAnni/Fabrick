package com.fabrick.conto.rest.to.api.model.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AccountData {

    @JsonProperty("accountId")
    String accountId;
    @JsonProperty("iban")
    String iban;
    @JsonProperty("abiCode")
    String abiCode;
    @JsonProperty("cabCode")
    String cabCode;
    @JsonProperty("countryCode")
    String countryCode;
    @JsonProperty("internationalCin")
    String internationalCin;
    @JsonProperty("nationalCin")
    String nationalCin;
    @JsonProperty("account")
    String account;
    @JsonProperty("alias")
    String alias;
    @JsonProperty("productName")
    String productName;
    @JsonProperty("holderName")
    String holderName;

    @JsonProperty("activatedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date activatedDate;

    @JsonProperty("currency")
    String currency;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAbiCode() {
        return abiCode;
    }

    public void setAbiCode(String abiCode) {
        this.abiCode = abiCode;
    }

    public String getCabCode() {
        return cabCode;
    }

    public void setCabCode(String cabCode) {
        this.cabCode = cabCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getInternationalCin() {
        return internationalCin;
    }

    public void setInternationalCin(String internationalCin) {
        this.internationalCin = internationalCin;
    }

    public String getNationalCin() {
        return nationalCin;
    }

    public void setNationalCin(String nationalCin) {
        this.nationalCin = nationalCin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "accountId='" + getAccountId() + '\'' +
                ", iban='" + getIban() + '\'' +
                ", abiCode='" + getAbiCode() + '\'' +
                ", cabCode='" + getCabCode() + '\'' +
                ", countryCode='" + getCountryCode() + '\'' +
                ", internationalCin='" + getInternationalCin() + '\'' +
                ", nationalCin='" + getNationalCin() + '\'' +
                ", account='" + getAccount() + '\'' +
                ", alias='" + getAlias() + '\'' +
                ", productName='" + getProductName() + '\'' +
                ", holderName='" + getHolderName() + '\'' +
                ", activatedDate=" + getActivatedDate() +
                ", currency='" + getCurrency() + '\'' +
                '}';
    }

    /*

    from: "https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0"

    accountId
            String
    The ID of the account.
            iban
            String
    The IBAN code of the account.

    abiCode
            String
    The abiCode code of the account.

    cabCode
            String
    The cabCode code of the account.

    countryCode
            String
    The countryCode code of the account.

    internationalCin
            String
    The internationalCin code of the account.

    nationalCin
            String
    The nationalCin code of the account.

    account
            String
    The account number. Substring of IBAN code.

    alias
            String
    The alias of the account, if any.

    productName
            String
    The account product name.

    holderName
            String
    The full name (or names) of the account holder (or holders).

    activatedDate
            Date
    The date on which the account was activated.

    currency
            String
    The native currency of the account.
*/

}
