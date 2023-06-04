package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.api.dto.VectorDtoV1;
import org.klimashin.ga.first.solution.application.data.VectorData;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VectorMapper {

    VectorData dtoToData(VectorDtoV1 dtoV1);
}
