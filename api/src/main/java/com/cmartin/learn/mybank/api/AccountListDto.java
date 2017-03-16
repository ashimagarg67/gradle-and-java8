package com.cmartin.learn.mybank.api;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cmartin on 16/03/2017.
 */
public class AccountListDto implements Serializable {
    private List<AccountDto> accounts;
    private LocalDate localDate;

    public AccountListDto(List<AccountDto> accounts) {
        this.accounts = accounts;
        this.localDate = LocalDate.now();
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
