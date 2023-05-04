package org.klimashin.ga.first.solution.domain.model;

import org.klimashin.ga.first.solution.domain.math.Point;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PointParticle {

    double mass;
    Point position;
}
