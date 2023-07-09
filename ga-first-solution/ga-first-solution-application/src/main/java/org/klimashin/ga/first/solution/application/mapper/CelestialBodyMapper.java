package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                OrbitMapper.class
        })
public interface CelestialBodyMapper {

    CelestialBodyEntity domainToEntity(CelestialBody domain);

    CelestialBody entityToDomain(CelestialBodyEntity entity);
}
