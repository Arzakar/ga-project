package org.klimashin.ga.first.solution.domain.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

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
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);
        var firstParticle = new PointParticle(100, firstPoint);
        var secondParticle = new PointParticle(200, secondPoint);

        var result = Physics.gravitationForceMagnitude(firstParticle, secondParticle);

        assertThat(result).isEqualTo(0.000_000_049_439_259, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void gravitationForce_shouldReturnGravitationForceVector() {
        var firstPoint = new Point(2, 3, 4);
        var secondPoint = new Point(5, 6, 7);
        var firstParticle = new PointParticle(100, firstPoint);
        var secondParticle = new PointParticle(200, secondPoint);

        var result = Physics.gravitationForce(firstParticle, secondParticle);

        var precision = Math.pow(10, 15);
        assertThat(result)
                .returns(0.000_000_028_543_770, value -> Math.round(value.getX() * precision) / precision)
                .returns(0.000_000_028_543_770, value -> Math.round(value.getY() * precision) / precision)
                .returns(0.000_000_028_543_770, value -> Math.round(value.getZ() * precision) / precision);
    }
}