package com.cmartin.learn.mybank.web.controller;

import com.cmartin.learn.mybank.api.*;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Created by cmartin on 10/07/16.
 */
@RestController
public class BankController {
    public static final String WELCOME_MESSAGE = "Hello, this is 'gradle-and-java8' root context: ";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FilterManager filterManager;

    @Autowired
    private BankService bankService;

    @Autowired
    public BankController(BankService bankApi) {
        this.bankService = bankApi;
    }

    @GetMapping(value = "/accounts/{accountId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountDto> getAccount(@PathVariable final String accountId) {

        this.logger.debug("input: accountId={}", accountId);

        //TODO validate id => UUID

        final Optional<AccountDto> account = this.bankService.getAccount(UUID.fromString(accountId));

        this.logger.debug("output: {}", account.toString());

        return account.isPresent() ? new ResponseEntity<>(account.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/accounts/{accountId}/transactions",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccountTransactionDto>> getAccountTransactions(@PathVariable final String accountId) {

        this.logger.debug("input: accountId={}", accountId);

        final List<AccountTransactionDto> accountTransactions =
                this.bankService.getAccountTransactions(UUID.fromString(accountId));

        this.logger.debug("output: retrieved {} accountTransactions", accountTransactions.size());

        return new ResponseEntity<>(accountTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/{accountId}/transactions/{transactionId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountTransactionDto> getAccountTransaction(
            @PathVariable final String accountId, @PathVariable final String transactionId) {

        this.logger.debug("input: accountId={}, transactionId={}", accountId, transactionId);

        final Optional<AccountTransactionDto> accountTransaction =
                this.bankService.getAccountTransaction(UUID.fromString(transactionId));

        this.logger.debug("output: accountTransaction {}", accountTransaction.get().toString());

        return new ResponseEntity<>(accountTransaction.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountListDto> getAccounts(
            @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {

        this.logger.debug("input: pageSize={}", pageSize);

        AccountFilter filter = this.filterManager.buildAccoutFilter();
        final List<AccountDto> accounts = this.bankService.getAccounts((filter));

        this.logger.debug("output: retrieved {} accounts", accounts.size());

        return new ResponseEntity<>(new AccountListDto(accounts), HttpStatus.OK);
    }


    @GetMapping(value = "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable final String userId) {

        this.logger.debug("input: userId={}", userId);

        final Optional<UserDto> user = this.bankService.getUser(UUID.fromString(userId));

        this.logger.debug("output: {}", user.get().toString());

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }


    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {

        this.logger.debug("input: pageSize={}", pageSize);

        UserFilter filter = this.filterManager.buildUserFilter();
        final List<UserDto> users = this.bankService.getUsers(filter);

        this.logger.debug("output: retrieved {} users", users.size());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/{accountId}/users",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAccountUsers(@PathVariable final String accountId) {

        this.logger.debug("input: accountId={}", accountId);

        final List<UserDto> users = this.bankService.getAccountUsers(UUID.fromString(accountId));

        this.logger.debug("output: retrieved {} users", users.size());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping(value = "/contracts",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContractDto>> getContracts(
            @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {

        this.logger.debug("input: pageSize={}", pageSize);

        ContractFilter filter = this.filterManager.buildContractFilter();
        final List<ContractDto> contracts = this.bankService.getContracts((filter));

        this.logger.debug("output: retrieved {} contracts", contracts.toString());

        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }


    @GetMapping(value = "/")
    public ResponseEntity<String> getRootContext() {
        return new ResponseEntity<>(
                join(WELCOME_MESSAGE,
                        LocalDateTime.now().toString()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/accounts")
    public ResponseEntity<?> createAccount(@RequestBody final AccountDto accountDto) {
        //TODO
        this.logger.debug("input: {}", accountDto);

        final Try<AccountDto> account = this.bankService.createAccount(accountDto);

        this.logger.debug("output: {}", account);

        if (account.isSuccess()) {
            return new ResponseEntity<>(account.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<?> updateAcconut(@PathVariable final String id, @RequestBody final AccountDto accountDto) {
        this.logger.debug("input: id={}, {}", id, accountDto);

        final Try<UUID> idTry = this.bankService.findAccountById(UUID.fromString(id));

        if (idTry.isSuccess()) {
            final Try<AccountDto> updatedAccount = this.bankService.updateAccount(accountDto);
            if (updatedAccount.isSuccess()) {
                this.logger.debug("output: {}", updatedAccount);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable final String accountId) {
        this.logger.debug("input: id={}", accountId);

        final Try<UUID> id = this.bankService.deleteAccount(UUID.fromString(accountId));

        this.logger.debug("output: id={}", id);

        if (id.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }
}
