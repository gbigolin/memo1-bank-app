package com.aninfo.model;

import javax.persistence.*;

@Entity
@Table(name ="Account_Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String transactionType;

    @OneToOne
    @JoinColumn(name = "Bank_Account", referencedColumnName = "CBU")
    private Account cbuAssociated;

    public Transaction(){
    }

    public Transaction( Account cbuAssociated, String transactionType, Double amount ) {
        this.cbuAssociated = cbuAssociated;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Transaction( Long id, Account cbuAssociated, String transactionType, Double amount ) {
        this.id = id; // en el primer transaction no esta ya que se crea ahi
        this.cbuAssociated = cbuAssociated;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Account getCbuAssociated() { return cbuAssociated;}

    public void setCbuAssociated (Account cbuAssociated){ this.cbuAssociated = cbuAssociated;}

    public String getTransactionType(){ return transactionType;}

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {return amount;}

    public void setAmount(Double amount) { this.amount = amount;}

}

