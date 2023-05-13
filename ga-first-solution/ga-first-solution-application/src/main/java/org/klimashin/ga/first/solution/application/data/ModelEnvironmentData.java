package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelEnvironmentData {

    CelestialBodyData centralBody;
    List<CelestialBodyData> celestialBodies;
    SpacecraftData spacecraft;

    CommandProfileData commandProfile;
    TargetStateData targetState;
}
