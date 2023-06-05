package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.api.dto.InitialStateRequestDtoV1;
import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateCreationData;
import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateData;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                CelestialBodyMapper.class,
                CommandProfileMapper.class,
                SpacecraftMapper.class,
                PointMapper.class,
                TargetStateMapper.class,
                VectorMapper.class
        })
public interface InitialStateMapper {

    @Mapping(target = "resultState", ignore = true)
    @Mapping(target = "spacecraftPositionX", source = "spacecraft.position.x")
    @Mapping(target = "spacecraftPositionY", source = "spacecraft.position.y")
    @Mapping(target = "spacecraftPositionZ", source = "spacecraft.position.z")
    @Mapping(target = "spacecraftSpeedX", source = "spacecraft.speed.x")
    @Mapping(target = "spacecraftSpeedY", source = "spacecraft.speed.y")
    @Mapping(target = "spacecraftSpeedZ", source = "spacecraft.speed.z")
    @Mapping(target = "commandProfileType", source = "commandProfile")
    @Mapping(target = "commandProfilePayload", source = "commandProfile")
    @Mapping(target = "targetStateType", source = "targetState")
    @Mapping(target = "targetStatePayload", source = "targetState")
    @Mapping(target = "celestialBodies", ignore = true)
    InitialStateEntity dataToEntity(InitialStateData data);

    @Transactional
    @Mapping(target = "resultStateId", ignore = true)
    @Mapping(target = "spacecraft.position.x", source = "spacecraftPositionX")
    @Mapping(target = "spacecraft.position.y", source = "spacecraftPositionY")
    @Mapping(target = "spacecraft.position.z", source = "spacecraftPositionZ")
    @Mapping(target = "spacecraft.speed.x", source = "spacecraftSpeedX")
    @Mapping(target = "spacecraft.speed.y", source = "spacecraftSpeedY")
    @Mapping(target = "spacecraft.speed.z", source = "spacecraftSpeedZ")
    @Mapping(target = "commandProfile", source = ".")
    @Mapping(target = "targetState", source = ".")
    @Mapping(target = "celestialBodies", ignore = true)
    InitialStateData entityToData(InitialStateEntity entity);

    @Mapping(target = "commandProfile", ignore = true)
    @Mapping(target = "targetState", source = ".")
    InitialStateCreationData requestDtoV1ToCreationData(InitialStateRequestDtoV1 requestDtoV1);
}
