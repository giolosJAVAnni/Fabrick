package com.fabrick.conto.rest.to.api.model.account;

import com.fabrick.conto.rest.to.api.model.transactions.TransactionData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountsPayload {

    @JsonProperty("list")
    List<AccountData> list;

    public List<AccountData> getList() {
        return list;
    }

    public void setList(List<AccountData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AccountsPayload{" +
                "list=" + getList() +
                '}';
    }
}
