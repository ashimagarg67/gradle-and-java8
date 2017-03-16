package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.api.AccountDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by cmartin on 16/03/2017.
 */
class DtoFactory {
    public static List<AccountDto> newAccountList() {

        List<AccountDto> list = Arrays.asList(
                newAccount("11112222334444444444", 101.50),
                newAccount("22223333445555555555", 0.00),
                newAccount("33334444556666666666", 2020.77)
        );
        return list;
    }

    public static AccountDto newAccount(final String number, final Double balance) {
        AccountDto account = new AccountDto(newUUID(), "alias-" + number, number, newBigDecimal(balance));
        return account;
    }

    public static BigDecimal newBigDecimal(final Double balance) {
        BigDecimal number = BigDecimal.valueOf(balance);
        return number;
    }

    public static UUID newUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
