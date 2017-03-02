package com.cmartin.learn;

/**
 * Created by cmartin on 28/02/2017.
 */
public class CarDto {
    private String manufacturer;
    private String seatCount;
    private Long serial;
    private String engineCode;
    private String cylinderCount;
    private String uCode;
    private String pistonCaliber;

    public CarDto() {
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getCylinderCount() {
        return cylinderCount;
    }

    public void setCylinderCount(String cylinderCount) {
        this.cylinderCount = cylinderCount;
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode;
    }

    public String getPistonCaliber() {
        return pistonCaliber;
    }

    public void setPistonCaliber(String pistonCaliber) {
        this.pistonCaliber = pistonCaliber;
    }
}
