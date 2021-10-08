package com.fabrick.conto.rest.to.api.model.transactions.savedata;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class TransactionModelTable {

    public TransactionModelTable() { }
    public TransactionModelTable(Long accountId, String data, String accountingDate)
    {
     setAccountId(accountId);
     setData(data);
     setAccountingDate(accountingDate);
     setCreatedAt(Timestamp.from(Instant.now()));
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

    @Column(name = "created_at", columnDefinition = "Timestamp")
    private Timestamp createdAt;

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
    }

    @Override
    public String toString() {
        return "TransactionModelTable{" +
                "id=" + getId() +
                ", accountId=" + getAccountId() +
                ", data='" + getData() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", accountingDate='" + getAccountingDate() + '\'' +
                '}';
    }
}




