package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateCelestialBodyRequestDtoV1 {

    UUID celestialBodyId;
    Double trueAnomaly;
}
