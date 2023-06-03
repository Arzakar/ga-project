package org.klimashin.ga.first.solution.application.data.initial.state;

import org.klimashin.ga.first.solution.application.data.CelestialBodyData;
import org.klimashin.ga.first.solution.application.data.SpacecraftData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateData {

    UUID id;
    UUID resultStateId;
    CelestialBodyData centralBody;
    List<CelestialBodyData> celestialBodies;
    SpacecraftData spacecraft;
    CommandProfileData commandProfile;
    TargetStateData targetState;
}
