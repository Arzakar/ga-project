package org.klimashin.ga.first.solution.math.two.dimension.util;

import org.klimashin.ga.first.solution.math.two.dimension.model.Point2D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Points2D {

    public static double distanceBetween(Point2D firstPoint, Point2D secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }

    public static Point2D copy(Point2D point) {
        return Point2D.of(point.getX(), point.getY());
    }
}
