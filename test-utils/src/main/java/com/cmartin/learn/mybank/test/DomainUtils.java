package com.cmartin.learn.mybank.test;

import com.cmartin.learn.domain.Account;

import java.math.BigDecimal;
import java.util.UUID;

public class DomainUtils {

    public static UUID getAccountId() {
        return UUID.randomUUID();
    }

    public static Account newAccount(String number, String alias, Double balance) {
        Account a = new Account();
        a.setNumber(number);
        a.setAlias(alias);
        a.setBalance(BigDecimal.valueOf(balance));

        return a;
    }
}
