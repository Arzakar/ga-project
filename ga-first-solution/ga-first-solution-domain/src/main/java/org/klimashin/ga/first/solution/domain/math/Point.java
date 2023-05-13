package org.klimashin.ga.first.solution.domain.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Point {

    double x;
    double y;
    double z;

    public static Point of(double x, double y, double z) {
        return new Point(x, y, z);
    }

    public Point change(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Point change(Point referencePoint) {
        this.x = referencePoint.getX();
        this.y = referencePoint.getY();
        this.z = referencePoint.getZ();
        return this;
    }

    public Point move(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
        return this;
    }

    public Point copy() {
        return new Point(x, y, z);
    }
}
