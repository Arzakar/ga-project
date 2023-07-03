package org.klimashin.ga.first.solution.math.util;

import org.klimashin.ga.first.solution.math.model.Point2D;
import org.klimashin.ga.first.solution.math.model.Vector2D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Vectors2D {

    public static Vector2D between(Point2D startPoint, Point2D endPoint) {
        return Vector2D.of(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
    }

    public static Vector2D copy(Vector2D vector) {
        return Vector2D.of(vector.getX(), vector.getY());
    }

    public static Vector2D zero() {
        return Vector2D.zero();
    }
}
