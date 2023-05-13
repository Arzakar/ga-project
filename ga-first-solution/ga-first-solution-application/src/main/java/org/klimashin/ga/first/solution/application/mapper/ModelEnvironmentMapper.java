package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.ModelEnvironmentData;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                CelestialBodyMapper.class,
                CommandProfileMapper.class,
                SpacecraftMapper.class
        })
public interface ModelEnvironmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resultState", ignore = true)
    @Mapping(target = "spacecraftPositionX", source = "spacecraft.position.x")
    @Mapping(target = "spacecraftPositionY", source = "spacecraft.position.y")
    @Mapping(target = "spacecraftPositionZ", source = "spacecraft.position.z")
    @Mapping(target = "spacecraftSpeedX", source = "spacecraft.speed.x")
    @Mapping(target = "spacecraftSpeedY", source = "spacecraft.speed.y")
    @Mapping(target = "spacecraftSpeedZ", source = "spacecraft.speed.z")
    @Mapping(target = "commandProfileType", source = "commandProfile")
    @Mapping(target = "commandProfilePayload", source = "commandProfile")
    @Mapping(target = "targetStateType", source = "commandProfile")
    @Mapping(target = "targetStatePayload", source = "commandProfile")
    @Mapping(target = "celestialBodies", ignore = true)
    InitialStateEntity dataToInitialStateEntity(ModelEnvironmentData data);

}
