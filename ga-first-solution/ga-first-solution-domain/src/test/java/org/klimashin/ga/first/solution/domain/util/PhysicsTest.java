package org.klimashin.ga.first.solution.domain.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.klimashin.ga.first.solution.domain.TestUtils.testRound;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.util.math.model.Point2D;

import org.junit.jupiter.api.Test;

class PhysicsTest {

    @Test
    void gravitationParameter_shouldReturnGravitationParameterForDiscreteMass() {
        var result = Physics.gravitationParameter(250);

        assertThat(result).isEqualTo(0.000_000_016_685_750, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void gravitationParameter_shouldReturnGravitationParameter() {
        var result = Physics.gravitationParameter(250, 750);

        assertThat(result).isEqualTo(0.000_000_066_743_000, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void gravitationForceMagnitude_shouldReturnScalarForceValue() {
        var firstPoint = Point2D.of(2, 3);
        var secondPoint = Point2D.of(5, 6);
        var firstParticle = new PointParticle(100, firstPoint);
        var secondParticle = new PointParticle(200, secondPoint);

        var result = Physics.gravitationForceMagnitude(firstParticle, secondParticle);

        assertThat(result).isEqualTo(0.000_000_074_158_888, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void gravitationForce_shouldReturnGravitationForceVector() {
        var firstPoint = Point2D.of(2, 3);
        var secondPoint = Point2D.of(5, 6);
        var firstParticle = new PointParticle(100, firstPoint);
        var secondParticle = new PointParticle(200, secondPoint);

        var result = Physics.gravitationForce(firstParticle, secondParticle);

        var precision = 15;
        assertThat(result)
                .returns(0.000_000_052_438_253, value -> testRound(value.getX(), precision))
                .returns(0.000_000_052_438_253, value -> testRound(value.getY(), precision));
    }
}