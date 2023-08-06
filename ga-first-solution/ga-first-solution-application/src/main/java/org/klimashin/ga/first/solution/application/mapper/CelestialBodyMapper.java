package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CelestialBodyMapper {

//    @Mapping(target = "id", ignore = true)
//    CelestialBodyEntity domainToEntity(CelestialBody domain);
//
    default CelestialBody entityToDomain(CelestialBodyEntity entity) {
        if (entity == null) {
            return null;
        }

        var orbit = Orbit.builder()
                .semiMajorAxis(entity.getSemiMajorAxis())
                .eccentricity(entity.getEccentricity())
                .build();

        return CelestialBody.builder()
                .name(entity.getName())
                .mass(entity.getMass())
                .orbit(orbit)
                .build();

    }
}
