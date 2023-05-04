package org.klimashin.ga.first.solution.domain.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vector {

    double x;
    double y;
    double z;

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public static Vector of(double x, double y, double z) {
        return new Vector(x, y, z);
    }

    public Vector change(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public Vector subtract(Vector vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
        return this;
    }

    public Vector multiply(double ratio) {
        this.x *= ratio;
        this.y *= ratio;
        this.z *= ratio;
        return this;
    }

    public Vector divide(double ratio) {
        if (ratio == 0) {
            throw new ArithmeticException();
        }

        this.x /= ratio;
        this.y /= ratio;
        this.z /= ratio;
        return this;
    }

    public double getScalar() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Vector toUnit() {
        return divide(getScalar());
    }

    public Vector rotateByZ(double angleInRadian) {
        double newX = (Math.cos(angleInRadian) * this.x) + (-Math.sin(angleInRadian) * this.y);
        double newY = (Math.sin(angleInRadian) * this.x) + (Math.cos(angleInRadian) * this.y);
        double newZ = this.z;

        return change(newX, newY, newZ);
    }

    public Vector copy() {
        return new Vector(this);
    }
}
