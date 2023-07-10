package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrbitMapper {

    @Mapping(target = "id", ignore = true)
    OrbitEntity domainToEntity(Orbit domain);

    @Mapping(target = "inclination", ignore = true)
    @Mapping(target = "longitudeAscNode", ignore = true)
    @Mapping(target = "perihelionArgument", ignore = true)
    @Mapping(target = "trueAnomaly", ignore = true)
    @Mapping(target = "attractingBodyMass", ignore = true)
    @Mapping(target = "zeroEpoch", ignore = true)
    Orbit entityToDomain(OrbitEntity entity);
}
