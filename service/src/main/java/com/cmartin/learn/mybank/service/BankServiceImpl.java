package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.BankRepository;
import com.cmartin.learn.mybank.api.*;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.cmartin.learn.mybank.service.DtoFactory.newAccountList;
import static com.cmartin.learn.mybank.service.DtoFactory.newUUID;

/**
 * Created by cmartin on 10/07/16.
 */
@Service
public class BankServiceImpl implements BankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // TODO @Autowired
    private BankRepository bankRepository;

    public BankServiceImpl(final BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Try<OperationOutputDto> operation(final OperationInputDto operationInputDto) {
        return Try.success(new OperationOutputDto("dummyProperty"));
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
        // new ArrayList<>();
        return newAccountList();
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
    public Try<AccountDto> createAccount(AccountDto accountDto) {
        this.logger.debug("input: {}", accountDto);

        //TIDI
        AccountDto newDto = new AccountDto(newUUID(), accountDto.getAlias(), accountDto.getNumber(), accountDto.getBalance());
        return Try.success(newDto);
    }

    @Override
    public Try<UUID> deleteAccount(final UUID accountId) {
        return Try.success(accountId);
    }

    @Override
    public List<AccountTransactionDto> getAccountTransactions(UUID accountId) {
        return new ArrayList<>();
    }

    @Override
    public Optional<AccountTransactionDto> getAccountTransaction(UUID accountTransactionId) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getUser(final UUID userId) {
        return Optional.empty();
    }

    @Override
    public List<UserDto> getUsers(final UserFilter filter) {
        return new ArrayList<>();
    }

    @Override
    public List<UserDto> getAccountUsers(final UUID accountId) {
        return new ArrayList<>();
    }
}
