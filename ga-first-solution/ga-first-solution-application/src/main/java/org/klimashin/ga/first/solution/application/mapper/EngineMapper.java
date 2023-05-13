package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.EngineData;
import org.klimashin.ga.first.solution.application.entity.EngineEntity;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EngineMapper {

    EngineEntity dataToEntity(EngineData engineData);

    EngineData entityToData(EngineEntity entity);
}
