package com.acmebank.accountmanager.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class TransferFundsRequest {
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Double amount;

    public Integer getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Integer fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Integer getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Integer toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}