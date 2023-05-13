package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.SpacecraftData;
import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                EngineMapper.class
        })
public interface SpacecraftMapper {

    @Mapping(target = "startFuelMass", source = "fuelMass")
    SpacecraftEntity dataToEntity(SpacecraftData data);

    @Mapping(target = "position", ignore = true)
    @Mapping(target = "speed", ignore = true)
    @Mapping(target = "acceleration", ignore = true)
    @Mapping(target = "mass", source = ".")
    @Mapping(target = "fuelMass", source = "startFuelMass")
    SpacecraftData entityToData(SpacecraftEntity entity);

    default Double getMass(SpacecraftEntity entity) {
        return entity.getDryMass() + entity.getStartFuelMass();
    }
}
