package com.cmartin.learn;

/**
 * Created by cmartin on 01/03/2017.
 */
public abstract class CarMapperDecorator implements CarMapper {
    private final CarMapper delegate;

    public CarMapperDecorator(CarMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public CarDto carToCarDto(Car car) {
        CarDto dto = delegate.carToCarDto(car);
        dto.setuCode(car.getEngine().getUniversalCode().toString());
        System.out.println("mapper decorator");
        return dto;
    }
}
