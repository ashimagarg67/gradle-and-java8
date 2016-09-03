package com.cmartin.learn.mybank.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.cmartin.learn.mybank.constants.Constants.ES_DATE_FORMAT;

/**
 * Created by cmartin on 21/08/16.
 */
public class AccountTransactionDto extends ResourceDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal amount;
    /*TODO add serialization library to the classpath
    https://github.com/FasterXML/jackson-datatype-jsr310
     */
    @JsonFormat(pattern = ES_DATE_FORMAT)
    private LocalDateTime dateTime;


    public AccountTransactionDto(UUID id, BigDecimal amount, LocalDateTime dateTime) {
        super(id);
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", this.getId())
                .append("amount", this.amount)
                .append("dateTime", this.dateTime)
                .toString();
    }

}
