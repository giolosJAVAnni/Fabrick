package com.fabrick.conto.rest.to.api.model.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionsPayload {

    @JsonProperty("list")
    List<TransactionData> list;

    public List<TransactionData> getList() {
        return list;
    }

    public void setList(List<TransactionData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TransactionsPayload{" +
                "list=" + getList() +
                '}';
    }
}
