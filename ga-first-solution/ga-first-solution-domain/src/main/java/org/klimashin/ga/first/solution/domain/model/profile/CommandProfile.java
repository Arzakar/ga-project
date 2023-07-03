package org.klimashin.ga.first.solution.domain.model.profile;

import org.klimashin.ga.first.solution.util.math.model.Vector2D;

public interface CommandProfile {

    Vector2D getThrustForceDirection(long currentTime);
}
