package com.cmartin.learn.domain;

import java.io.Serializable;
import java.util.UUID;

public class Contract implements Serializable {
    private UUID id;
    private String alias;
    private String number;

    public Contract() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
