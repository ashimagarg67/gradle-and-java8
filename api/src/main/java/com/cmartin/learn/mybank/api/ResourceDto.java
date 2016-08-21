package com.cmartin.learn.mybank.api;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by cmartin on 21/08/16.
 */
public abstract class ResourceDto implements Serializable {
    private UUID id;

    public ResourceDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
