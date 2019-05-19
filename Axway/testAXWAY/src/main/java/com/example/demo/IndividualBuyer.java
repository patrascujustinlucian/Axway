package com.example.demo;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class IndividualBuyer extends Buyer {

    @Column(name = "dateRegistered" )
    private java.sql.Date dateRegistered;

    @Column(name = "buyerPersonalIdentification" )
    private String buyerPersonalIdentification;

    @JoinColumn(name = "transactions_id")
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transactions> transactions = new ArrayList<>();

    public List<Transactions> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getBuyerPersonalIdentification() {
        return buyerPersonalIdentification;
    }

    public void setBuyerPersonalIdentification(String buyerPersonalIdentification) {
        this.buyerPersonalIdentification = buyerPersonalIdentification;
    }
}
