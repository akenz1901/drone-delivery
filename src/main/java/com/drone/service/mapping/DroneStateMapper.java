package com.drone.service.mapping;


import com.drone.data.model.Drone;
import com.drone.data.model.dto.DroneDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DroneStateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoDroneToExistingDrone(DroneDto droneDto, @MappingTarget Drone drone);
}
