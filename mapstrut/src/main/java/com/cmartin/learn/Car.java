package com.cmartin.learn;

import java.math.BigInteger;

/**
 * Created by cmartin on 28/02/2017.
 */
public class Car {
    private String make;
    private String numberOfSeats;
    private BigInteger serialNumber;

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getNumberOfSeats() {

        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }
}
