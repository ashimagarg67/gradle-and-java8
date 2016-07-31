package com.cmartin.learn.mybank.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.cmartin.learn.mybank.constants.Constants.ES_DATE_FORMAT;

/**
 * Created by cmartin on 09/07/16.
 */
public class OperationOutputDto implements Serializable {
    private String dummyProperty;
    @JsonFormat(pattern = ES_DATE_FORMAT)
    private LocalDateTime dateTime;

    public OperationOutputDto(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }

    public String getDummyProperty() {
        return dummyProperty;
    }

    public void setDummyProperty(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("dummyProperty", dummyProperty)
                .append("dateTime", dateTime)
                .toString();
    }
}
