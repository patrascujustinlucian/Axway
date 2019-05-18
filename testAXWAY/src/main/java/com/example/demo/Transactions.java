package com.example.demo;

import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Entity
@Table(name = "Transactions")
public class Transactions {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "transactionNumber", nullable = false)
    private int transactionNumber;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "description", nullable = false)
    private String description;



    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }


    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
