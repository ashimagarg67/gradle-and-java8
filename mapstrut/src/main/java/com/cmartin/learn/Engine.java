package com.cmartin.learn;

import java.util.List;
import java.util.UUID;

/**
 * Created by cmartin on 01/03/2017.
 */
public class Engine {
    private Long code;
    private Integer pistonCount;
    private List<Piston> pistons;
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

    public List<Piston> getPistons() {
        return pistons;
    }

    public void setPistons(List<Piston> pistons) {
        this.pistons = pistons;
    }
}
