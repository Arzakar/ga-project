package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.entity.EngineEntity;
import org.klimashin.ga.first.solution.domain.model.Engine;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EngineMapper {

    EngineEntity domainToEntity(Engine domain);

    Engine entityToDomain(EngineEntity entity);
}
