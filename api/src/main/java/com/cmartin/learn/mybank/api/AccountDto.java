package com.cmartin.learn.mybank.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by cmartin on 16/07/16.
 */
public class AccountDto extends ContractDto {
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;


    public AccountDto(UUID id, String alias, String number, BigDecimal balance) {
        super(id, alias);
        this.number = number;
        this.balance = balance;
    }

    /**
     *
     * @return
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public String getNumber() {
        return number;
    }
}
