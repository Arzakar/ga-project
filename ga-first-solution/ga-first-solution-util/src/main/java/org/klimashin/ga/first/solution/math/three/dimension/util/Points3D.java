package org.klimashin.ga.first.solution.math.three.dimension.util;

import org.klimashin.ga.first.solution.math.three.dimension.model.Point3D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Points3D {

    public static double distanceBetween(Point3D firstPoint, Point3D secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2)
                + Math.pow(firstPoint.getZ() - secondPoint.getZ(), 2));
    }

    public static Point3D copy(Point3D point) {
        return Point3D.of(point.getX(), point.getY(), point.getZ());
    }
}
