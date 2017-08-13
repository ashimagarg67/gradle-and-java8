package com.cmartin.learn.mybank.service;


import com.cmartin.learn.mybank.api.AccountDto;
import com.cmartin.learn.mybank.test.TestUtils;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class ValidationTest {

    private AccountDtoValidator accountDtoValidator;

    public static final UUID ID = UUID.randomUUID();
    public static final String ALIAS = "account-alias";
    public static final String NUMBER = "ES1212341234121234567890";
    public static final BigDecimal BALANCE = BigDecimal.valueOf(0L, 2);


    @Before
    public void setUp() {
        this.accountDtoValidator = new AccountDtoValidator();
    }

    @Test
    public void testValidAccountDto() {
        AccountDto accountDto = TestUtils.newAccountDto(ID, ALIAS, NUMBER, BALANCE);
        Validation<Seq<String>, AccountDto> result = accountDtoValidator.validateAccountDto(accountDto);

        Assert.assertTrue(result.isValid());
        Assert.assertTrue(result.get().getId().equals(ID));
        Assert.assertTrue(result.get().getAlias().equals(ALIAS));
        Assert.assertTrue(result.get().getNumber().equals(NUMBER));
        Assert.assertTrue(result.get().getBalance().equals(BALANCE));
    }

    @Test
    public void testInValidAccountDto() {
        AccountDto accountDto = TestUtils.newAccountDto(null, "", "", null);
        Validation<Seq<String>, AccountDto> result = accountDtoValidator.validateAccountDto(accountDto);

        Assert.assertTrue(result.isInvalid());
    }

}
