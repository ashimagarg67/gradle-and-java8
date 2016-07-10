package com.cmartin.learn.mybank.api;

/**
 * Created by cmartin on 09/07/16.
 */
public interface BankApi {
    OperationOutputDto operation(OperationInputDto operationInputDto);
}
