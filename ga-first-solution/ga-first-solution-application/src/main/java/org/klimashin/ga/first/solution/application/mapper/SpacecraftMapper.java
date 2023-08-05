package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SpacecraftMapper {

//    @Mapping(target = "mass", expression = "java(entity.getDryMass() + entity.getStartFuelMass())")
//    @Mapping(target = "position", ignore = true)
//    @Mapping(target = "speed", ignore = true)
//    @Mapping(target = "fuelMass", expression = "java(entity.getStartFuelMass())")
//    Spacecraft entityToDomain(SpacecraftEntity entity);
}
