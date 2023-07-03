package org.klimashin.ga.first.solution.util.math.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point2D {

    double x;
    double y;

    public static Point2D of(double x, double y) {
        return new Point2D(x, y);
    }

    public Point2D move(Vector2D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        return this;
    }

    public Point2D copy() {
        return new Point2D(this.x, this.y);
    }
}
