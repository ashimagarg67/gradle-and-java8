package com.cmartin.learn.mybank.api;

import javaslang.control.Try;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by cmartin on 09/07/16.
 */
public interface BankService {
    /**
     * @param operationInputDto
     * @return
     */
    Try<OperationOutputDto> operation(OperationInputDto operationInputDto);


    /**
     * @param filter
     * @return
     */
    List<ContractDto> getContracts(ContractFilter filter);

    /**
     * @param userId
     * @return
     */
    List<ContractDto> getUserContracts(UUID userId);

    /**
     * @param filter
     * @return
     */
    List<AccountDto> getAccounts(AccountFilter filter);

    /**
     * @param userId
     * @return
     */
    List<AccountDto> getUserAccounts(UUID userId);

    /**
     * @param accountId
     * @return
     */
    Optional<AccountDto> getAccount(UUID accountId);

    /**
     * @param accountId
     * @return
     */
    List<AccountTransactionDto> getAccountTransactions(UUID accountId);

    /**
     * @param accountTransactionId
     * @return
     */
    Optional<AccountTransactionDto> getAccountTransaction(UUID accountTransactionId);

    /**
     * Retrieves a @{@link UserDto} by its internal id
     *
     * @param userId
     * @return
     */
    Optional<UserDto> getUser(UUID userId);

    /**
     * Retrieves a @{@link UserDto} list depending on a @{@link UserFilter}
     *
     * @param filter
     * @return
     */
    List<UserDto> getUsers(UserFilter filter);

    /**
     * Retrieves the account owner or participants.
     *
     * @param accountId
     * @return
     */
    List<UserDto> getAccountUsers(UUID accountId);


}
