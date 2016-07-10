package com.cmartin.learn.mybank.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cmartin on 09/07/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BankApiTest {

    @Mock
    protected BankApi api;

    @Before
    public void setup() {

    }

    @Test
    public void testOperation() {
        OperationInputDto operationInputDto = new OperationInputDto();
        OperationOutputDto operationOutputDto = new OperationOutputDto();

        when(this.api.operation(operationInputDto))
                .thenReturn(new OperationOutputDto());

        final OperationOutputDto operation = this.api.operation(operationInputDto);

        verify(this.api).operation(operationInputDto);

        Assert.assertNotNull(operationOutputDto);
    }
}