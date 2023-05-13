package org.klimashin.ga.first.solution.application.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultStateData {

    UUID id;
    InitialStateData initialState;
    SpacecraftData spacecraft;
    ResultStatusData status;
    String description;
    Map<CelestialBodyData, OrbitData> celestialBodies;
}
