package org.klimashin.ga.modeler.dim2.model;

import org.klimashin.ga.lib.math.dim2.domain.Point;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(of = "name")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBody implements MaterialPoint {

    Point position;
    double mass;

    String name;
}
