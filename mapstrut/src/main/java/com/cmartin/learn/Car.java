package com.cmartin.learn;

import java.math.BigInteger;

/**
 * Created by cmartin on 28/02/2017.
 */
public class Car {
    private String make;
    private String numberOfSeats;
    private BigInteger serialNumber;
    private Engine engine;

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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
