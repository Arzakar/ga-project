package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.EngineData;
import org.klimashin.ga.first.solution.application.entity.EngineEntity;
import org.klimashin.ga.first.solution.domain.model.Engine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EngineMapper {

    Engine dataToDomain(EngineData data);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    EngineEntity domainToEntity(Engine domain);

    Engine entityToDomain(EngineEntity entity);

    EngineData entityToData(EngineEntity entity);
}
