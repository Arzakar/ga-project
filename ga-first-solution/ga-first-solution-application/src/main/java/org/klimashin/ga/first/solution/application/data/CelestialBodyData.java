package org.klimashin.ga.first.solution.application.data;

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
    PointData position;
}
