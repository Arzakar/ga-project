package org.klimashin.ga.first.solution.domain.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    void change_shouldChange() {
        var point = new Point(1, 1, 1);

        var result = point.change(2, 3, 4);

        assertThat(result)
                .isSameAs(point)
                .returns(2d, Point::getX)
                .returns(3d, Point::getY)
                .returns(4d, Point::getZ);
    }

    @Test
    void move_shouldMove() {
        var point = new Point(1, 1, 1);
        var vector = new Vector(2, 3, 4);

        var result = point.move(vector);

        assertThat(result)
                .isSameAs(point)
                .returns(3d, Point::getX)
                .returns(4d, Point::getY)
                .returns(5d, Point::getZ);
    }
}