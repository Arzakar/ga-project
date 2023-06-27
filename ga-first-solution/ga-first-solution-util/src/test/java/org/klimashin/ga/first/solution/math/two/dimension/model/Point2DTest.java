package org.klimashin.ga.first.solution.math.two.dimension.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class Point2DTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void of_shouldCreateNewPoint() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();

        var result = Point2D.of(x, y);

        assertThat(result)
                .returns(x, Point2D::getX)
                .returns(y, Point2D::getY);
    }

    @Test
    void move_shouldMove() {
        var startX = easyRandom.nextDouble();
        var startY = easyRandom.nextDouble();
        var point = Point2D.of(startX, startY);
        var vector = Vector2D.of(easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = point.move(vector);

        assertThat(result)
                .isSameAs(point)
                .returns(startX + vector.getX(), Point2D::getX)
                .returns(startY + vector.getY(), Point2D::getY);
    }

    @Test
    void copy_shouldCopy() {
        var original = Point2D.of(easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = original.copy();

        assertThat(result)
                .isNotSameAs(original)
                .returns(original.getX(), Point2D::getX)
                .returns(original.getY(), Point2D::getY);
    }
}