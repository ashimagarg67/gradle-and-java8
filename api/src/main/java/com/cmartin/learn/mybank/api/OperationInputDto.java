package com.cmartin.learn.mybank.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by cmartin on 09/07/16.
 */
public class OperationInputDto implements Serializable {
    private String dummyProperty;

    public OperationInputDto(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }

    public String getDummyProperty() {
        return dummyProperty;
    }

    public void setDummyProperty(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("dummyProperty", dummyProperty)
                .toString();
    }
}
