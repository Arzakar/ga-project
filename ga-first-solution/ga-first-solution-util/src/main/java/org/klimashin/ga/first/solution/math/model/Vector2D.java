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
public class Vector2D {

    double x;
    double y;

    public static Vector2D of(double x, double y) {
        return new Vector2D(x, y);
    }

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    public Vector2D add(Vector2D vector) {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector2D subtract(Vector2D vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        return this;
    }

    public Vector2D multiply(double ratio) {
        this.x *= ratio;
        this.y *= ratio;
        return this;
    }

    public Vector2D divide(double ratio) {
        if (ratio == 0) {
            throw new ArithmeticException("Ошибка при делении вектора на 0");
        }

        this.x /= ratio;
        this.y /= ratio;
        return this;
    }

    public double getScalar() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2D toUnit() {
        return divide(getScalar());
    }

    public Vector2D rotate(double angleInRadian) {
        double newX = (Math.cos(angleInRadian) * this.x) + (-Math.sin(angleInRadian) * this.y);
        double newY = (Math.sin(angleInRadian) * this.x) + (Math.cos(angleInRadian) * this.y);

        this.x = newX;
        this.y = newY;

        return this;
    }

    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }
}
