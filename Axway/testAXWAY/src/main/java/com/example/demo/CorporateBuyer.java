package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transactions")
public class CorporateBuyer extends Buyer{



    @Column(name = "address" )
    private String address;

    @Column(name = "companyIdentification" )
    private String companyIdentification;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyIdentification() {
        return companyIdentification;
    }

    public void setCompanyIdentification(String companyIdentification) {
        this.companyIdentification = companyIdentification;
    }

}
