package com.cmartin.learn.mybank.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by cmartin on 16/07/16.
 */
public class AccountDto extends ContractDto {
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;

    public AccountDto() {
    }

    public AccountDto(UUID id, String alias, String number, BigDecimal balance) {
        super(id, alias);
        this.number = number;
        this.balance = balance;
    }

    /**
     * @return
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @return
     */
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("number", number)
                .append("balance", balance)
                .toString();
    }
}
