package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.math.Vector;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Vectors {

    public static Vector between(Point startPoint, Point endPoint) {
        return new Vector(endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                endPoint.getZ() - startPoint.getZ());
    }

    public static Vector between(PointParticle startParticle, PointParticle endParticle) {
        return between(startParticle.getPosition(), endParticle.getPosition());
    }

    public static Vector copy(Vector vector) {
        return new Vector(vector);
    }

    public static Vector zero() {
        return new Vector(0, 0, 0);
    }
}
