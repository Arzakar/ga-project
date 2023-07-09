package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                EngineMapper.class
        })
public interface SpacecraftMapper {

    SpacecraftEntity domainToEntity(Spacecraft domain);

    Spacecraft entityToDomain(SpacecraftEntity entity);
}
