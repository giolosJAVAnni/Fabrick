package com.fabrick.conto.rest.to.api.model.transactions.savedata;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "transactions_history")
public class TransactionHistoryModelTable {

    public TransactionHistoryModelTable() { }
    public TransactionHistoryModelTable(Long accountId, String data, String accountingDate)
    {
     this.accountId = accountId;
     this.data = data;
     this.accountingDate = accountingDate;
     this.historicizedAt = Timestamp.from(Instant.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "account_id")
    private Long accountId;

    //@Column(name = "data_transaction", columnDefinition = "jsonb")   ***** not supported from H2....
    @Column(name = "data_transaction", columnDefinition = "json")
    private String data;

    @Column(name = "historicized_at", columnDefinition = "Timestamp")
    private Timestamp historicizedAt;

    @Column(name = "accountingDate")
    private String accountingDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Timestamp getHistoricizedAt() {
        return historicizedAt;
    }

    public void setHistoricizedAt(Timestamp historicizedAt) {
        this.historicizedAt = historicizedAt;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
    }

    @Override
    public String toString() {
        return "TransactionHistoryModelTable{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", data='" + data + '\'' +
                ", historicizedAt=" + historicizedAt +
                ", accountingDate='" + accountingDate + '\'' +
                '}';
    }
}




