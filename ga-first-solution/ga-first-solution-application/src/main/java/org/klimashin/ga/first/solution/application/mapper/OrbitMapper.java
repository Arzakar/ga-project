package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.OrbitData;
import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrbitMapper {

    OrbitEntity dataToEntity(OrbitData data);

    @Mapping(target = "trueAnomaly", ignore = true)
    @Mapping(target = "attractingBodyMass", ignore = true)
    OrbitData entityToData(OrbitEntity entity);

    @Mapping(target = "zeroEpoch", ignore = true)
    Orbit dataToDomain(OrbitData data);
}
