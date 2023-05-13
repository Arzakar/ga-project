package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.CelestialBodyData;
import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {
                OrbitMapper.class
        })
public interface CelestialBodyMapper {

    CelestialBodyEntity dataToEntity(CelestialBodyData data);

    @Mapping(target = "position", ignore = true)
    CelestialBodyData entityToData(CelestialBodyEntity entity);

    List<CelestialBodyData> entityToData(List<CelestialBodyEntity> entities);
}
