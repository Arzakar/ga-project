package org.klimashin.ga.first.solution.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orbit {

    double semiMajorAxis;
    double eccentricity;
    double inclination;
    double longitudeAscNode;
    double perihelionArgument;

    @Setter
    double trueAnomaly;

    double attractingBodyMass;
    long zeroEpoch;

    public double getFocalParameter() {
        return semiMajorAxis * (1 - eccentricity);
    }
}
