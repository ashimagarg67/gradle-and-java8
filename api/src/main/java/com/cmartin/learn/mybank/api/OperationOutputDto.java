package com.cmartin.learn.mybank.api;

import java.io.Serializable;

/**
 * Created by cmartin on 09/07/16.
 */
public class OperationOutputDto implements Serializable {
    private String dummyProperty;

    public OperationOutputDto(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }

    public String getDummyProperty() {
        return dummyProperty;
    }

    public void setDummyProperty(String dummyProperty) {
        this.dummyProperty = dummyProperty;
    }
}
