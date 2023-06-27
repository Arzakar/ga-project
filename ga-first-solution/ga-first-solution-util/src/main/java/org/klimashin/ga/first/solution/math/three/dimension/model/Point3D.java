package org.klimashin.ga.first.solution.math.three.dimension.model;

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
public class Point3D {

    double x;
    double y;
    double z;

    public static Point3D of(double x, double y, double z) {
        return new Point3D(x, y, z);
    }

    public Point3D move(Vector3D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
        return this;
    }

    public Point3D copy() {
        return new Point3D(this.x, this.y, this.z);
    }
}
