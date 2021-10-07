package com.fabrick.conto.rest.to.api.model.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TransactionsRequest {

    @JsonProperty("fromAccountingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date fromAccountingDate;

    @JsonProperty("toAccountingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date toAccountingDate;

    public Date getFromAccountingDate() {
        return fromAccountingDate;
    }

    public void setFromAccountingDate(Date fromAccountingDate) {
        this.fromAccountingDate = fromAccountingDate;
    }

    public Date getToAccountingDate() {
        return toAccountingDate;
    }

    public void setToAccountingDate(Date toAccountingDate) {
        this.toAccountingDate = toAccountingDate;
    }

    @Override
    public String toString() {
        return "TransactionsRequest{" +
                "fromAccountingDate=" + fromAccountingDate +
                ", toAccountingDate=" + toAccountingDate +
                '}';
    }


    public void jsonToString(){

    }

}
