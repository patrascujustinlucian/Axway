package com.example.demo;


import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class Buyer {

    public Buyer(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "buyerName", nullable = false)
    private String buyerName;

    @Column(name = "value", nullable = false)
    private int value;

    @JoinColumn(name = "transactions_id")
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transactions> transactions = new ArrayList<>();



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }


    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Buyer getBuyer(){
        return this;
    }

}
