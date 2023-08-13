package org.klimashin.ga.lib.math.dim2.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class VectorTest {

    private final EasyRandom easyRandom = new EasyRandom();
    private final double precision = 10E-6;

    @Test
    void of_shouldCreateNewVector() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();

        var result = Vector.of(x, y);

        assertThat(result)
                .returns(x, Vector::getX)
                .returns(y, Vector::getY);
    }

    @Test
    void zero_shouldCreateNewZeroVector() {
        var result = Vector.zero();

        assertThat(result)
                .returns(0d, Vector::getX)
                .returns(0d, Vector::getY);
    }

    @Test
    void add_shouldChangeCoordinates() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var addedX = easyRandom.nextDouble();
        var addedY = easyRandom.nextDouble();

        var originVector = Vector.of(originX, originY);
        var addedVector = Vector.of(addedX, addedY);

        var result = originVector.add(addedVector);

        assertThat(result)
                .isSameAs(originVector)
                .returns(originX + addedX, Vector::getX)
                .returns(originY + addedY, Vector::getY);

        assertThat(addedVector)
                .returns(addedX, Vector::getX)
                .returns(addedY, Vector::getY);
    }

    @Test
    void subtract_shouldChangeCoordinates() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var subtractedX = easyRandom.nextDouble();
        var subtractedY = easyRandom.nextDouble();

        var originVector = Vector.of(originX, originY);
        var subtractedVector = Vector.of(subtractedX, subtractedY);

        var result = originVector.subtract(subtractedVector);

        assertThat(result)
                .isSameAs(originVector)
                .returns(originX - subtractedX, Vector::getX)
                .returns(originY - subtractedY, Vector::getY);

        assertThat(subtractedVector)
                .returns(subtractedX, Vector::getX)
                .returns(subtractedY, Vector::getY);
    }

    @Test
    void multiply_shouldChangeCoordinates() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var ratio = easyRandom.nextDouble();

        var originVector = Vector.of(originX, originY);

        var result = originVector.multiply(ratio);

        assertThat(result).isSameAs(originVector);
        assertThat(result.getX()).isEqualTo(originX * ratio, withPrecision(precision));
        assertThat(result.getY()).isEqualTo(originY * ratio, withPrecision(precision));
    }

    @Test
    void divide_shouldChangeCoordinates() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var ratio = easyRandom.nextDouble();

        var originVector = Vector.of(originX, originY);

        var result = originVector.divide(ratio);

        assertThat(result).isSameAs(originVector);
        assertThat(result.getX()).isEqualTo(originX / ratio, withPrecision(precision));
        assertThat(result.getY()).isEqualTo(originY / ratio, withPrecision(precision));
    }

    @Test
    void getScalar_shouldReturnScalarValue() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();
        var vector = Vector.of(x, y);

        var result = vector.getScalar();

        assertThat(result).isEqualTo(Math.sqrt(x * x + y * y), withPrecision(precision));

        assertThat(vector)
                .returns(x, Vector::getX)
                .returns(y, Vector::getY);
    }

    @Test
    void toUnit_shouldTransformToUnit() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();
        var vector = Vector.of(x, y);

        var scalar = Math.sqrt(x * x + y * y);

        var result = vector.toUnit();

        assertThat(result).isSameAs(vector);
        assertThat(result.getX()).isEqualTo(x / scalar, withPrecision(precision));
        assertThat(result.getY()).isEqualTo(y / scalar, withPrecision(precision));
    }
}
