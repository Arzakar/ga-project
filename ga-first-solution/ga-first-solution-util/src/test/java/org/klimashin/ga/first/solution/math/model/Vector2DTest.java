package org.klimashin.ga.first.solution.math.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.klimashin.ga.first.solution.TestUtils.round;

import org.klimashin.ga.first.solution.util.math.model.Vector2D;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class Vector2DTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void of_shouldCreateNewVector() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();

        var result = Vector2D.of(x, y);

        assertThat(result)
                .returns(x, Vector2D::getX)
                .returns(y, Vector2D::getY);
    }

    @Test
    void zero_shouldCreateNewZeroVector() {
        var result = Vector2D.zero();

        assertThat(result)
                .returns(0d, Vector2D::getX)
                .returns(0d, Vector2D::getY);
    }

    @Test
    void add_shouldAddVector() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var addedX = easyRandom.nextDouble();
        var addedY = easyRandom.nextDouble();

        var originVector = Vector2D.of(originX, originY);
        var addedVector = Vector2D.of(addedX, addedY);

        var result = originVector.add(addedVector);

        assertThat(result)
                .isSameAs(originVector)
                .returns(originX + addedX, Vector2D::getX)
                .returns(originY + addedY, Vector2D::getY);

        assertThat(addedVector)
                .returns(addedX, Vector2D::getX)
                .returns(addedY, Vector2D::getY);
    }

    @Test
    void subtract_shouldSubtractVector() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var subtractedX = easyRandom.nextDouble();
        var subtractedY = easyRandom.nextDouble();

        var originVector = Vector2D.of(originX, originY);
        var subtractedVector = Vector2D.of(subtractedX, subtractedY);

        var result = originVector.subtract(subtractedVector);

        assertThat(result)
                .isSameAs(originVector)
                .returns(originX - subtractedX, Vector2D::getX)
                .returns(originY - subtractedY, Vector2D::getY);

        assertThat(subtractedVector)
                .returns(subtractedX, Vector2D::getX)
                .returns(subtractedY, Vector2D::getY);
    }

    @Test
    void multiply_shouldMultiplyVector() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var ratio = easyRandom.nextDouble();

        var originVector = Vector2D.of(originX, originY);

        var result = originVector.multiply(ratio);

        var precision = 6;
        assertThat(result)
                .isSameAs(originVector)
                .returns(round(originX * ratio, precision), value -> round(value.getX(), precision))
                .returns(round(originY * ratio, precision), value -> round(value.getY(), precision));
    }

    @Test
    void divide_shouldDivideVector() {
        var originX = easyRandom.nextDouble();
        var originY = easyRandom.nextDouble();
        var ratio = easyRandom.nextDouble();

        var originVector = Vector2D.of(originX, originY);

        var result = originVector.divide(ratio);

        var precision = 6;
        assertThat(result)
                .isSameAs(originVector)
                .returns(round(originX / ratio, precision), value -> round(value.getX(), precision))
                .returns(round(originY / ratio, precision), value -> round(value.getY(), precision));
    }

    @Test
    void getScalar_shouldReturnScalar() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();
        var vector = Vector2D.of(x, y);

        var expectedResult = Math.sqrt(x * x + y * y);
        var actualResult = vector.getScalar();

        assertThat(actualResult)
                .isEqualTo(expectedResult, withPrecision(0.000001));

        assertThat(vector)
                .returns(x, Vector2D::getX)
                .returns(y, Vector2D::getY);
    }

    @Test
    void toUnit_shouldTransformToUnit() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();
        var vector = Vector2D.of(x, y);

        var scalar = Math.sqrt(x * x + y * y);
        var expectedResult = Vector2D.of(x / scalar, y / scalar);
        var actualResult = vector.toUnit();

        var precision = 6;
        assertThat(actualResult)
                .isSameAs(vector)
                .returns(round(expectedResult.getX(), precision), value -> round(value.getX(), precision))
                .returns(round(expectedResult.getY(), precision), value -> round(value.getY(), precision));
    }
}