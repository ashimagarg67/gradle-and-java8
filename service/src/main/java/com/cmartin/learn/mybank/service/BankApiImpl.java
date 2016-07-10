package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.api.BankApi;
import com.cmartin.learn.mybank.api.OperationInputDto;
import com.cmartin.learn.mybank.api.OperationOutputDto;
import org.springframework.stereotype.Service;

/**
 * Created by cmartin on 10/07/16.
 */
@Service
public class BankApiImpl implements BankApi {
    @Override
    public OperationOutputDto operation(final OperationInputDto operationInputDto) {
        return new OperationOutputDto("dummyProperty");
    }
}
