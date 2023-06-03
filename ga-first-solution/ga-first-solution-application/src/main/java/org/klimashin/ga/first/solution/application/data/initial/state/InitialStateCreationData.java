package org.klimashin.ga.first.solution.application.data.initial.state;

import org.klimashin.ga.first.solution.application.data.PointData;
import org.klimashin.ga.first.solution.application.data.VectorData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateCreationData {

    UUID centralBodyId;
    Map<UUID, Double> celestialBodies;
    UUID spacecraftId;
    PointData spacecraftPosition;
    VectorData spacecraftSpeed;
    CommandProfileData commandProfile;
    TargetStateData targetState;
}
