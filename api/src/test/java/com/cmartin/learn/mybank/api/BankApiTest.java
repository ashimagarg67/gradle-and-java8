package com.cmartin.learn.mybank.api;

import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cmartin on 09/07/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BankApiTest {

    protected String successDummyProperty = "OkDummyProperty";
    protected String failureDummyProperty = "KoDummyProperty";
    protected OperationOutputDto successOutputDto = new OperationOutputDto(successDummyProperty);
    protected OperationOutputDto failureOutputDto = new OperationOutputDto(failureDummyProperty);
    protected OperationInputDto operationInputDto;

    @Mock
    protected BankService api;

    @Before
    public void setup() {
        this.operationInputDto = new OperationInputDto(successDummyProperty);
    }

    @Test
    public void testOperationOk() {
        when(this.api.operation(this.operationInputDto))
                .thenReturn(Try.success(this.successOutputDto));

        final Try<OperationOutputDto> operation = this.api.operation(this.operationInputDto);

        verify(this.api).operation(this.operationInputDto);

        OperationOutputDto resultDto = operation.getOrElse(this.failureOutputDto);

        Assert.assertTrue(operation.isSuccess());
        Assert.assertEquals(resultDto.getDummyProperty(), this.successDummyProperty);
    }

    @Test
    public void testOperationKo() {
        when(this.api.operation(this.operationInputDto))
                .thenReturn(Try.failure(new ServiceException(failureDummyProperty)));

        final Try<OperationOutputDto> operation = this.api.operation(this.operationInputDto);

        verify(this.api).operation(this.operationInputDto);

        OperationOutputDto resultDto = operation.getOrElse(this.failureOutputDto);

        Assert.assertTrue(operation.isFailure());
        Assert.assertEquals(resultDto.getDummyProperty(), this.failureDummyProperty);
        Assert.assertEquals(operation.getCause().getMessage(), this.failureDummyProperty);
    }
}