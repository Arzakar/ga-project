package org.klimashin.ga.first.solution.domain.model.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class FixedVectorDeviationCommandProfileTest {

    @Test
    void shouldCorrectConstruct() {
        var firstPair = new Pair(0, 10);
        var secondPair = new Pair(11, 20);
        var thirstPair = new Pair(25, 30);
        var intervals = Map.of(secondPair, 5d, thirstPair, 10d, firstPair, 20d);

        var profile = new FixedVectorDeviationCommandProfile(null, null, intervals);

        assertThat(profile.getTimeBounds()).hasSize(3);
        assertThat(profile.getTimeBounds()).first().isEqualTo(firstPair);
        assertThat(profile.getTimeBounds()).last().isEqualTo(thirstPair);
    }

    @ParameterizedTest
    @MethodSource("thrustForceDirectionTestSource")
    void getThrustForceDirection_shouldReturnVector(long currentTime, Vector expectedVector) {
        var spacecraft = Spacecraft.builder()
                        .position(new Point(0, 0, 0))
                        .build();
        var solar = CelestialBody.builder()
                        .position(new Point(10, 0, 0))
                        .build();
        var intervals = Map.of(
                Pair.of(0, 10), Math.PI / 2,
                Pair.of(11, 20), Math.PI,
                Pair.of(25, 30), - Math.PI / 2);

        var profile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);

        assertThat(profile.getThrustForceDirection(currentTime))
                .returns(expectedVector.getX(), Vector::getX)
                .returns(expectedVector.getY(), Vector::getY)
                .returns(expectedVector.getZ(), Vector::getZ);
    }

    private static Stream<Arguments> thrustForceDirectionTestSource() {
        return Stream.of(
                Arguments.of(5, Vector.of(0, 1, 0)),
                Arguments.of(15, Vector.of(-1, 0 , 0)),
                Arguments.of(25, Vector.of(0, -1, 0)),
                Arguments.of(45, Vector.of(0, -1, 0))
        );
    }

    @Test
    void getThrustForceDirection_shouldThrowException() {
        var spacecraft = Spacecraft.builder()
                .position(new Point(0, 0, 0))
                .build();
        var solar = CelestialBody.builder()
                .position(new Point(10, 0, 0))
                .build();
        var intervals = Map.of(
                Pair.of(0, 10), Math.PI / 2,
                Pair.of(11, 20), Math.PI,
                Pair.of(25, 30), - Math.PI / 2);

        var profile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> profile.getThrustForceDirection(10));
    }
}