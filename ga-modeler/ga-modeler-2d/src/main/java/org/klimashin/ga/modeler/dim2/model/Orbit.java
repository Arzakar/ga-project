package org.klimashin.ga.modeler.dim2.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orbit {

    final CelestialBody attractingBody;

    final double semiMajorAxis;
    final double eccentricity;

    double trueAnomaly;

    private Orbit(CelestialBody attractingBody, double semiMajorAxis, double eccentricity, double trueAnomaly) {
        this.attractingBody = attractingBody;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.trueAnomaly = trueAnomaly;
    }
}
