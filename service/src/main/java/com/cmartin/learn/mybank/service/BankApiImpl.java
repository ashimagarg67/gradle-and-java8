package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.BankRepository;
import com.cmartin.learn.mybank.api.AccountDto;
import com.cmartin.learn.mybank.api.AccountFilter;
import com.cmartin.learn.mybank.api.BankApi;
import com.cmartin.learn.mybank.api.ContractDto;
import com.cmartin.learn.mybank.api.ContractFilter;
import com.cmartin.learn.mybank.api.OperationInputDto;
import com.cmartin.learn.mybank.api.OperationOutputDto;
import com.cmartin.learn.mybank.api.UserDto;
import com.cmartin.learn.mybank.api.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by cmartin on 10/07/16.
 */
@Service
public class BankApiImpl implements BankApi {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public OperationOutputDto operation(final OperationInputDto operationInputDto) {
        return new OperationOutputDto("dummyProperty");
    }

    @Override
    public List<ContractDto> getContracts(final ContractFilter filter) {
        return null;
    }

    @Override
    public List<ContractDto> getUserContracts(final UUID userId) {
        return null;
    }

    @Override
    public List<AccountDto> getAccounts(final AccountFilter filter) {
        return null;
    }

    @Override
    public List<AccountDto> getUserAccounts(final UUID userId) {
        return null;
    }

    @Override
    public Optional<AccountDto> getAccount(final UUID accountId) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getUser(final UUID userId) {
        return Optional.empty();
    }

    @Override
    public List<UserDto> getUsers(final UserFilter filter) {
        return null;
    }

    @Override
    public List<UserDto> getAccountUsers(final AccountDto account) {
        return null;
    }
}
