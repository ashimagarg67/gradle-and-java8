package com.cmartin.learn.mybank.service;

import com.cmartin.learn.domain.Account;
import com.cmartin.learn.mybank.api.AccountDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO remove
public class SimpleMapper {
    static Account mapServiceToRepository(final AccountDto dto) {
        Account entity = new Account();
        entity.setAlias(dto.getAlias());
        entity.setBalance(dto.getBalance());
//        entity.setNumber(dto.getNumber());
        entity.setNumber(ServiceHelper.buildSeudoIBANString());

        return entity;
    }

    static AccountDto mapRepositoryToService(final Account entity) {
        return new AccountDto(entity.getId(), entity.getAlias(), entity.getNumber(), entity.getBalance());
    }

     static List<AccountDto> mapRepositoryToService(Iterable<Account> accounts) {
        return StreamSupport.stream(accounts.spliterator(),false)
                .map(a -> mapRepositoryToService(a)).collect(Collectors.toList());
    }
}
