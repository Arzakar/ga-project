package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.application.data.condition.TargetStateTypeData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileTypeData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateData {

    UUID id;
    ResultStateData resultState;
    CelestialBodyData centralBody;
    SpacecraftData spacecraft;
    CommandProfileTypeData commandProfileType;
    String commandProfilePayload;
    TargetStateTypeData targetStateType;
    String targetStatePayload;
    Map<CelestialBodyData, OrbitData> celestialBodies;
}
