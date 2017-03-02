package com.cmartin.learn;

import java.util.UUID;

/**
 * Created by cmartin on 01/03/2017.
 */
public class CarConverters {
    public String asString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    public String asString(Piston piston) {
        return piston != null ? piston.getDiameter().toString() : null;
    }
}
