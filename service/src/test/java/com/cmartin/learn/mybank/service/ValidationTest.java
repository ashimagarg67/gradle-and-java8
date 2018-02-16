package com.cmartin.learn.mybank.service;


import com.cmartin.learn.mybank.api.AccountDto;
import com.cmartin.learn.mybank.test.TestUtils;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(result.isValid());
        assertThat(result.get().getId()).isEqualByComparingTo(ID);
        assertThat(result.get().getAlias()).isEqualTo(ALIAS);
        assertThat(result.get().getNumber()).isEqualTo(NUMBER);
        assertThat(result.get().getBalance()).isEqualByComparingTo(BALANCE);
    }

    @Test
    public void testInValidAccountDto() {
        AccountDto accountDto = TestUtils.newAccountDto(null, "", "", null);
        Validation<Seq<String>, AccountDto> result = accountDtoValidator.validateAccountDto(accountDto);

        assertThat(result.isInvalid());
    }

}
