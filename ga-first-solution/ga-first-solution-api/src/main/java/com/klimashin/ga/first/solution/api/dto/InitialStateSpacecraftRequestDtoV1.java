package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateSpacecraftRequestDtoV1 {

    UUID spacecraftId;
    UUID launchCelestialBodyId;
    PositionDtoV1 position;
    VectorDtoV1 speed;
}
