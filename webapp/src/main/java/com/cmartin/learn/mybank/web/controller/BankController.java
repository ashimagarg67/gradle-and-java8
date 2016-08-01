package com.cmartin.learn.mybank.web.controller;

import com.cmartin.learn.mybank.api.AccountDto;
import com.cmartin.learn.mybank.api.AccountFilter;
import com.cmartin.learn.mybank.api.BankService;
import com.cmartin.learn.mybank.api.ContractDto;
import com.cmartin.learn.mybank.api.ContractFilter;
import com.cmartin.learn.mybank.api.UserDto;
import com.cmartin.learn.mybank.api.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cmartin on 10/07/16.
 */
@RestController
public class BankController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FilterManager filterManager;

    @Autowired
    private BankService bankService;

    @Autowired
    public BankController(BankService bankApi) {
        this.bankService = bankApi;
    }


    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {

        this.logger.debug("input: pageSize={}", pageSize);

        UserFilter filter = this.filterManager.buildUserFilter();
        final List<UserDto> users = this.bankService.getUsers(filter);

        this.logger.debug("output: retrieved {} useres", users.size());

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


    @GetMapping(value = "/accounts",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AccountDto>> getAccounts(
            @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {

        this.logger.debug("input: pageSize={}", pageSize);

        AccountFilter filter = this.filterManager.buildAccoutFilter();
        final List<AccountDto> accounts = this.bankService.getAccounts((filter));

        this.logger.debug("output: retrieved {} accounts", accounts.toString());

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> getRootContext() {
        return new ResponseEntity<>("Hello, this is 'gralde-and-java8' root context: " + LocalDateTime.now(),
                HttpStatus.OK);
    }

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }
}
