package com.cmartin.learn;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CarConverters.class})
//@DecoratedWith(CarMapperDecorator.class)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "make", target = "manufacturer"),
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "serialNumber", target = "serial"),
            @Mapping(source = "engine.code", target = "engineCode"),
            @Mapping(source = "engine.pistonCount", target = "cylinderCount"),
            @Mapping(source = "engine.universalCode", target = "uCode"),
            @Mapping(source = "engine.pistons", target = "pistons")

    })
    CarDto carToCarDto(Car car);

}