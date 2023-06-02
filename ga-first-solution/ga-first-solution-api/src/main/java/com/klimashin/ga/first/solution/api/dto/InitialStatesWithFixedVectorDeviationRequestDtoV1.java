package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStatesWithFixedVectorDeviationRequestDtoV1 {

    UUID centralBodyId;
    List<InitialStateCelestialBodyRequestDtoV1> celestialBodies;
    InitialStateSpacecraftRequestDtoV1 spacecraft;
    FixedVectorDeviationProfileDtoV1 commandProfilePayload;
    TargetStateTypeDtoV1 targetStateType;
    String targetStatePayload;
}
