package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.domain.math.Point;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBodyData {

    UUID id;
    OrbitData orbit;
    String name;
    Double mass;
    Point position;
}
