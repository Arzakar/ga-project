package org.klimashin.ga.first.solution.domain.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

import org.junit.jupiter.api.Test;

class PointParticlesTest {

    @Test
    void distanceBetween_shouldReturnDistanceBetweenPoints() {
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);

        var result = PointParticles.distanceBetween(firstPoint, secondPoint);

        assertThat(result)
                .isEqualTo(5.196152, withPrecision(0.000001));

        assertThat(firstPoint)
                .returns(2d, Point::getX)
                .returns(3d, Point::getY)
                .returns(4d, Point::getZ);

        assertThat(secondPoint)
                .returns(5d, Point::getX)
                .returns(6d, Point::getY)
                .returns(7d, Point::getZ);
    }

    @Test
    void distanceBetween_shouldReturnDistanceBetweenPointParticles() {
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);
        var firstParticle = new PointParticle(10, firstPoint);
        var secondParticle = new PointParticle(20, secondPoint);

        var result = PointParticles.distanceBetween(firstParticle, secondParticle);

        assertThat(result)
                .isEqualTo(5.196152, withPrecision(0.000001));

        assertThat(firstParticle)
                .returns(10d, PointParticle::getMass)
                .extracting(PointParticle::getPosition)
                .isSameAs(firstPoint)
                .returns(2d, Point::getX)
                .returns(3d, Point::getY)
                .returns(4d, Point::getZ);

        assertThat(secondParticle)
                .returns(20d, PointParticle::getMass)
                .extracting(PointParticle::getPosition)
                .isSameAs(secondPoint)
                .returns(5d, Point::getX)
                .returns(6d, Point::getY)
                .returns(7d, Point::getZ);
    }
}