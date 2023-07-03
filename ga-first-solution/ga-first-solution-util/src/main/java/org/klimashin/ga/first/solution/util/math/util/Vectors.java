package org.klimashin.ga.first.solution.util.math.util;

import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Point3D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.math.model.Vector3D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Vectors {

    public static Vector2D between(Point2D startPoint, Point2D endPoint) {
        return Vector2D.of(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
    }

    public static Vector3D between(Point3D startPoint, Point3D endPoint) {
        return Vector3D.of(endPoint.getX() - startPoint.getX(),
                endPoint.getY() - startPoint.getY(),
                endPoint.getZ() - startPoint.getZ());
    }

    public static Vector2D copy(Vector2D vector) {
        return Vector2D.of(vector.getX(), vector.getY());
    }

    public static Vector3D copy(Vector3D vector) {
        return Vector3D.of(vector.getX(), vector.getY(), vector.getZ());
    }
}
