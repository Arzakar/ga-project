package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Points {

    public static double distanceBetween(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2)
                + Math.pow(firstPoint.getZ() - secondPoint.getZ(), 2));
    }

    public static double distanceBetween(PointParticle firstParticle, PointParticle secondParticle) {
        return distanceBetween(firstParticle.getPosition(), secondParticle.getPosition());
    }

    public static Point copy(Point point) {
        return Point.of(point.getX(), point.getY(), point.getZ());
    }
}
