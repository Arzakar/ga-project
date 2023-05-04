package org.klimashin.ga.first.solution.domain;

import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.klimashin.ga.first.solution.domain.model.condition.TargetState;
import org.klimashin.ga.first.solution.domain.model.profile.CommandProfile;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelEnvironment {

    PointParticle centralBody;
    List<CelestialBody> celestialBodies;
    Spacecraft spacecraft;

    CommandProfile commandProfile;
    TargetState targetState;
}
