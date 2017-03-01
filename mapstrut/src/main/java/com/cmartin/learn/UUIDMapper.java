package com.cmartin.learn;

import java.util.UUID;

/**
 * Created by cmartin on 01/03/2017.
 */
public class UUIDMapper {
    public String asString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
}
