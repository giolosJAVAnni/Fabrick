package com.fabrick.conto.rest.to.api.model.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TransactionData {

    @JsonProperty("transactionId")
    String transactionId;
    @JsonProperty("operationId")
    String operationId;
    @JsonProperty("accountingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date accountingDate;
    @JsonProperty("valueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date valueDate;
    @JsonProperty("type")
    TypeTransaction type;
    @JsonProperty("amount")
    Long amount;
    @JsonProperty("currency")
    String currency;
    @JsonProperty("description")
    String description;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "transactionId='" + transactionId + '\'' +
                ", operationId='" + operationId + '\'' +
                ", accountingDate=" + accountingDate +
                ", valueDate=" + valueDate +
                ", type=" + type +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    // I don't know the values of the 'Enumeration', so...
    static class TypeTransaction
    {
        public TypeTransaction(@JsonProperty("enumeration") String enumeration, @JsonProperty("value") String value){
            this.enumeration=enumeration;
            this.value=value;
        }

        @JsonProperty("enumeration")
        String enumeration;
        @JsonProperty("value")
        String value;

        public String getEnumeration() {
            return enumeration;
        }

        public void setEnumeration(String enumeration) {
            this.enumeration = enumeration;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TypeTransaction{" +
                    "enumeration='" + enumeration + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}

    /*
    from: "https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0"

    transactionId
    String
    The ID of the transaction. This is a unique ID for the transaction, valid to identify a transaction across all of your accounts provided by Banca Sella.

    operationId
    String
    The ID of the accounting operation. This ID matches multiple logically connected transactions (e.g., the money transfer with its fees).

    accountingDate
    Date
    The date on which the transaction was accounted on the account.

    valueDate
    Date
    The value date of the transaction.

    type
    Enumeration
    The type of the transaction.

    amount
    Number
    The amount of the transaction.

    currency
    String
    The currency of the transaction.

    description
    String
    The description of the transaction.
*/


