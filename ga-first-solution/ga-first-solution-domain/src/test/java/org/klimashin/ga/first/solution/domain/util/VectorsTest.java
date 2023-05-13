package org.klimashin.ga.first.solution.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

import org.junit.jupiter.api.Test;

class VectorsTest {

    @Test
    void between_shouldReturnVectorBetweenPoints() {
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);

        var result = Vectors.between(firstPoint, secondPoint);

        assertThat(result)
                .returns(3d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(3d, Vector::getZ);

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
    void between_shouldReturnVectorBetweenPointParticles() {
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);
        var firstParticle = new PointParticle(10, firstPoint);
        var secondParticle = new PointParticle(20, secondPoint);

        var result = Vectors.between(firstParticle, secondParticle);

        assertThat(result)
                .returns(3d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(3d, Vector::getZ);

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

    @Test
    void copy_shouldReturnNewEqualsVector() {
        var referenceVector = new Vector(2, 3, 4);

        var result = Vectors.copy(referenceVector);

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
    void zero_shouldCreateZeroVector() {
        var result = Vectors.zero();

        assertThat(result)
                .returns(0d, Vector::getX)
                .returns(0d, Vector::getY)
                .returns(0d, Vector::getZ);
    }
}