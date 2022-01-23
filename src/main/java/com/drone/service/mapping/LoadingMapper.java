package com.drone.service.mapping;

import com.drone.data.model.LoadingContainer;
import com.drone.data.model.dto.LoadingContainerDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring")
public interface LoadingMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoContainer(LoadingContainerDto loadingContainerDto, @MappingTarget LoadingContainer loadingContainer);

}
