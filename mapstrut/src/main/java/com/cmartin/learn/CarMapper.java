package com.cmartin.learn;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {UUIDMapper.class})
//@DecoratedWith(CarMapperDecorator.class)
public interface CarMapper {

    @Mappings({
            @Mapping(source = "make", target = "manufacturer"),
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "serialNumber", target = "serial"),
            @Mapping(source = "engine.code", target = "engineCode"),
            @Mapping(source = "engine.pistonCount", target = "cylinderCount"),
            @Mapping(source = "engine.universalCode", target = "uCode")
    })
    CarDto carToCarDto(Car car);


//    @Mapping(source = "name", target = "fullName")
//    PersonDto personToPersonDto(Person person);
}