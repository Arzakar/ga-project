package org.klimashin.ga.lib.math.dim2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class PointTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void of_shouldCreateNewPoint() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();

        var result = Point.of(x, y);

        assertThat(result)
                .returns(x, Point::getX)
                .returns(y, Point::getY);
    }

    @Test
    void move_shouldChangeCoordinates() {
        var startX = easyRandom.nextDouble();
        var startY = easyRandom.nextDouble();
        var point = Point.of(startX, startY);
        var vector = Vector.of(easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = point.move(vector);

        assertThat(result)
                .isSameAs(point)
                .returns(startX + vector.getX(), Point::getX)
                .returns(startY + vector.getY(), Point::getY);
    }

    @Test
    void copy_shouldReturnNewSimilarPoint() {
        var original = Point.of(easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = original.copy();

        assertThat(result)
                .isNotSameAs(original)
                .returns(original.getX(), Point::getX)
                .returns(original.getY(), Point::getY);
    }
}
