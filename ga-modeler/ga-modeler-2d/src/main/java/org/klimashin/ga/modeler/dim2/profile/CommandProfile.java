package org.klimashin.ga.modeler.dim2.profile;

import org.klimashin.ga.lib.math.dim2.domain.Vector;

public interface CommandProfile {

    Vector getThrustForceDirection(long currentTime);
}
