package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.util.math.model.Point2D;

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
    Point2D position;
}
