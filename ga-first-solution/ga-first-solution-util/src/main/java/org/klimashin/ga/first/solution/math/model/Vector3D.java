package org.klimashin.ga.first.solution.math.model;

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
public class Vector3D {

    double x;
    double y;
    double z;

    public static Vector3D of(double x, double y, double z) {
        return new Vector3D(x, y, z);
    }

    public static Vector3D zero() {
        return new Vector3D(0, 0, 0);
    }

    public Vector3D add(Vector3D vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public Vector3D subtract(Vector3D vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
        return this;
    }

    public Vector3D multiply(double ratio) {
        this.x *= ratio;
        this.y *= ratio;
        this.z *= ratio;
        return this;
    }

    public Vector3D divide(double ratio) {
        if (ratio == 0) {
            throw new ArithmeticException("Ошибка при делении вектора на 0");
        }

        this.x /= ratio;
        this.y /= ratio;
        this.z /= ratio;
        return this;
    }

    public double getScalar() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Vector3D toUnit() {
        return divide(getScalar());
    }

    public Vector3D rotate(double angleInRadian) {
        double newX = (Math.cos(angleInRadian) * this.x) + (-Math.sin(angleInRadian) * this.y);
        double newY = (Math.sin(angleInRadian) * this.x) + (Math.cos(angleInRadian) * this.y);

        this.x = newX;
        this.y = newY;

        return this;
    }

    public Vector3D copy() {
        return new Vector3D(this.x, this.y, this.z);
    }
}
