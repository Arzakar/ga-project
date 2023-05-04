package org.klimashin.ga.first.solution.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orbit {

    double semiMajorAxis;
    double eccentricity;
    double inclination;
    double longitudeAscNode;
    double periapsisArgument;
    double trueAnomaly;

    double attractingBodyMass;
    long zeroEpoch;
}
