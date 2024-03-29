package org.klimashin.ga.first.solution.util.math.util;

import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Point3D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Points {

    public static double distanceBetween(Point2D firstPoint, Point2D secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }

    public static double distanceBetween(Point3D firstPoint, Point3D secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2)
                + Math.pow(firstPoint.getZ() - secondPoint.getZ(), 2));
    }

    public static Point2D copy(Point2D point) {
        return Point2D.of(point.getX(), point.getY());
    }

    public static Point3D copy(Point3D point) {
        return Point3D.of(point.getX(), point.getY(), point.getZ());
    }
}
