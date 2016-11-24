package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.api.AccountDto;
import javaslang.collection.List;
import javaslang.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by cmartin on 24/11/2016.
 */
public class AccountDtoValidator {


    public Validation<List<String>, AccountDto> validateAccountDto(final AccountDto accountDto) {
        return Validation.combine(
                validateId(accountDto.getId()),
                validateAlias(accountDto.getAlias()),
                validateNumber(accountDto.getNumber()),
                validateBalance(accountDto.getBalance())
        ).ap(AccountDto::new);
    }


    private Validation<String, UUID> validateId(final UUID id) {
        return (id != null)
                ? Validation.valid(id)
                : Validation.invalid("el identificador no puede ser vacío");
    }

    private Validation<String, String> validateAlias(final String alias) {
        return StringUtils.isNotBlank(alias)
                ? Validation.valid(alias)
                : Validation.invalid("el identificador no puede ser vacío");
    }

    private Validation<String, String> validateNumber(final String alias) {
        return StringUtils.isNotBlank(alias)
                ? Validation.valid(alias)
                : Validation.invalid("el numero de cuenta no puede ser vacío");
    }

    private Validation<String, BigDecimal> validateBalance(final BigDecimal balance) {
        return (balance != null)
                ? Validation.valid(balance)
                : Validation.invalid("el saldo no puede ser vacío");
    }

}
