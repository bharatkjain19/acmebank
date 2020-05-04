package com.acmebank.accountmanager.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "ACCOUNTS")
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTNUMBER")
    private Integer accountNumber;
    @Column(name = "ACCOUNTBALANCE")
    private Double accountBalance;

    public Account(Integer accountNumber, Double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Integer getAccountNumber(int i) {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}