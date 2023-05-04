package org.klimashin.ga.first.solution.domain.model.profile;

import org.klimashin.ga.first.solution.domain.math.Vector;

public interface CommandProfile {

    Vector getThrustForceDirection(long currentTime);
}
