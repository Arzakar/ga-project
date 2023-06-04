package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.api.dto.PointDtoV1;
import org.klimashin.ga.first.solution.application.data.PointData;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PointMapper {

    PointData dtoV1ToData(PointDtoV1 dtoV1);
}
