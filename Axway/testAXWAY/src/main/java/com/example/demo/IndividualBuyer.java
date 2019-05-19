package com.example.demo;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Date;

public class IndividualBuyer extends Buyer {

    @Column(name = "dateRegistered" )
    private java.sql.Date dateRegistered;

    @Column(name = "buyerPersonalIdentification" )
    private String buyerPersonalIdentification;


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
