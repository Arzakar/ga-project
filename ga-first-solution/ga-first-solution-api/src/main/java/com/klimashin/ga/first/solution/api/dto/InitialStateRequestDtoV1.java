package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateRequestDtoV1 {

    UUID centralBodyId;
    Map<UUID, Double> celestialBodies;
    UUID spacecraftId;
    PointDtoV1 spacecraftPosition;
    VectorDtoV1 spacecraftSpeed;
    TargetStateTypeDtoV1 targetStateType;
    Object targetStatePayload;
}
