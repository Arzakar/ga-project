package org.klimashin.ga.first.solution.math.util;

import org.klimashin.ga.first.solution.math.model.Point3D;
import org.klimashin.ga.first.solution.math.model.Vector3D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Vectors3D {

    public static Vector3D between(Point3D startPoint, Point3D endPoint) {
        return Vector3D.of(endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                endPoint.getZ() - startPoint.getZ());
    }

    public static Vector3D copy(Vector3D vector) {
        return Vector3D.of(vector.getX(), vector.getY(), vector.getZ());
    }

    public static Vector3D zero() {
        return Vector3D.zero();
    }
}
