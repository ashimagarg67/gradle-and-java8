package com.cmartin.learn.domain;

import java.math.BigDecimal;

public class Account extends Contract {
    private BigDecimal balance;

    public Account() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
