package com.cmartin.learn.mybank.api;

/**
 * Created by cmartin on 09/07/16.
 */
public interface BankApi {
    /**
     *
     * @param operationInputDto
     * @return
     */
    OperationOutputDto operation(OperationInputDto operationInputDto);
}
