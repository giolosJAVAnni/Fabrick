package com.fabrick.conto.rest.to.api.model.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class BalancePayload {

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date date;

    @JsonProperty("balance")
    @JsonSerialize(using = CustomDoubleSerializer.class)
    Double balance;

    @JsonProperty("availableBalance")
    @JsonSerialize(using = CustomDoubleSerializer.class)
    Double availableBalance;

    @JsonProperty("currency")
    String currency;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getBalance() { return balance; }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "BalanceApiResponse{" +
                "date=" + date +
                ", balance=" + balance +
                ", availableBalance=" + availableBalance +
                ", currency='" + currency + '\'' +
                '}';
    }

}
