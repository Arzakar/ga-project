package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.CelestialBodyData;
import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                OrbitMapper.class
        })
public interface CelestialBodyMapper {

    @Mapping(target = "id", ignore = true)
    CelestialBodyEntity domainToEntity(CelestialBody domain);

    @Mapping(target = "position", ignore = true)
    CelestialBodyData entityToData(CelestialBodyEntity entity);

    CelestialBody entityToDomain(CelestialBodyEntity entity);
}
