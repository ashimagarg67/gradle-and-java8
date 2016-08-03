package com.cmartin.learn.mybank.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by cmartin on 16/07/16.
 */
public class ContractDto implements Serializable {
    private UUID id;
    private String alias="";

    public ContractDto(UUID id, String alias) {
        this.id = id;
        this.alias=alias;
    }

    public UUID getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("alias", alias)
                .toString();
    }
}
