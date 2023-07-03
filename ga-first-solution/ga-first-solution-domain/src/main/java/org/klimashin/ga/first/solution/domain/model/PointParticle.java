package org.klimashin.ga.first.solution.domain.model;

import org.klimashin.ga.first.solution.util.math.model.Point2D;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PointParticle {

    double mass;
    Point2D position;
}
