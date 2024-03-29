package org.klimashin.ga.lib.math.dim2.domain;

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
public class Point {

    double x;
    double y;

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public Point move(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        return this;
    }

    public Point copy() {
        return new Point(this.x, this.y);
    }
}
