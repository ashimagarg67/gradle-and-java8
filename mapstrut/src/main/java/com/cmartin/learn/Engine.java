package com.cmartin.learn;

import java.util.UUID;

/**
 * Created by cmartin on 01/03/2017.
 */
public class Engine {
    private Long code;
    private Integer pistonCount;
    private Piston piston;
    private UUID universalCode;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getPistonCount() {
        return pistonCount;
    }

    public void setPistonCount(Integer pistonCount) {
        this.pistonCount = pistonCount;
    }

    public UUID getUniversalCode() {
        return universalCode;
    }

    public void setUniversalCode(UUID universalCode) {
        this.universalCode = universalCode;
    }

    public Piston getPiston() {
        return piston;
    }

    public void setPiston(Piston piston) {
        this.piston = piston;
    }
}
