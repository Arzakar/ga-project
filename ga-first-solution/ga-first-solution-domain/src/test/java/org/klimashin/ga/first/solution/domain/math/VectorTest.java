package org.klimashin.ga.first.solution.domain.math;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.withPrecision;

import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    void constructor_shouldCreateByVector() {
        var referenceVector = new Vector(2, 3, 4);

        var result = new Vector(referenceVector);

        assertThat(result)
                .isNotSameAs(referenceVector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);

        assertThat(referenceVector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void of_shouldCreateVector() {
        var result = Vector.of(2, 3, 4);

        assertThat(result)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void change_shouldChange() {
        var vector = new Vector(1, 1, 1);

        var result = vector.change(2, 3, 4);

        assertThat(result)
                .isSameAs(vector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void add_shouldAddVector() {
        var vector = new Vector(1, 1, 1);
        var addedVector = new Vector(2, 3, 4);

        var result = vector.add(addedVector);

        assertThat(result)
                .isSameAs(vector)
                .returns(3d, Vector::getX)
                .returns(4d, Vector::getY)
                .returns(5d, Vector::getZ);

        assertThat(addedVector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void subtract_shouldSubtractVector() {
        var vector = new Vector(1, 1, 1);
        var subtractedVector = new Vector(2, 3, 4);

        var result = vector.subtract(subtractedVector);

        assertThat(result)
                .isSameAs(vector)
                .returns(-1d, Vector::getX)
                .returns(-2d, Vector::getY)
                .returns(-3d, Vector::getZ);

        assertThat(subtractedVector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void multiply_shouldMultiply() {
        var vector = new Vector(1, 1, 1);

        var result = vector.multiply(2);

        assertThat(result)
                .isSameAs(vector)
                .returns(2d, Vector::getX)
                .returns(2d, Vector::getY)
                .returns(2d, Vector::getZ);
    }

    @Test
    void divide_shouldDivide() {
        var vector = new Vector(1, 1, 1);

        var result = vector.divide(2);

        assertThat(result)
                .isSameAs(vector)
                .returns(0.5, Vector::getX)
                .returns(0.5, Vector::getY)
                .returns(0.5, Vector::getZ);
    }

    @Test
    void divide_shouldThrowArithmeticException() {
        var vector = new Vector(1, 1, 1);

        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> vector.divide(0))
                .withMessageContaining("Ошибка при делении вектора на 0");
    }

    @Test
    void getScalar_shouldReturnScalar() {
        var vector = new Vector(2, 3, 4);

        var result = vector.getScalar();

        assertThat(result)
                .isEqualTo(5.385165, withPrecision(0.000001));

        assertThat(vector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void toUnit_shouldTransformToUnit() {
        var vector = new Vector(2, 3, 4);

        var result = vector.toUnit();

        var precision = Math.pow(10, 6);
        assertThat(result)
                .isSameAs(vector)
                .returns(0.371391, value -> Math.round(value.getX() * precision) / precision)
                .returns(0.557086, value -> Math.round(value.getY() * precision) / precision)
                .returns(0.742781, value -> Math.round(value.getZ() * precision) / precision);
    }

    @Test
    void rotateByZ_shouldRotateVector() {
        var vector = new Vector(2, 3, 4);

        var result = vector.rotateByZ(Math.PI / 2);

        assertThat(result)
                .isSameAs(vector)
                .returns(-3d, Vector::getX)
                .returns(2d, Vector::getY)
                .returns(4d, Vector::getZ);
    }

    @Test
    void copy_shouldReturnNewEqualsVector() {
        var vector = new Vector(2, 3, 4);

        var result = vector.copy();

        assertThat(result)
                .isNotSameAs(vector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);

        assertThat(vector)
                .returns(2d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(4d, Vector::getZ);
    }
}