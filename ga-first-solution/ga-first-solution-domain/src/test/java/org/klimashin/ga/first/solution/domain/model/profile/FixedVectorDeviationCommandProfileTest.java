package org.klimashin.ga.first.solution.domain.model.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.klimashin.ga.first.solution.domain.TestUtil;
import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.model.LongPair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class FixedVectorDeviationCommandProfileTest {

    @Test
    void shouldCorrectConstruct() {
        var firstPair = new LongPair(0, 10);
        var secondPair = new LongPair(11, 20);
        var thirstPair = new LongPair(25, 30);
        var intervals = Map.of(secondPair, 5d, thirstPair, 10d, firstPair, 20d);

        var profile = new FixedVectorDeviationCommandProfile(null, null, intervals);

        assertThat(profile.getTimeBounds()).hasSize(3);
        assertThat(profile.getTimeBounds()).first().isEqualTo(firstPair);
        assertThat(profile.getTimeBounds()).last().isEqualTo(thirstPair);
    }

    @ParameterizedTest
    @MethodSource("thrustForceDirectionTestSource")
    void getThrustForceDirection_shouldReturnVector(long currentTime, Vector expectedVector) {
        var spacecraft = TestUtil.spacecraftGenerator()
                .withPosition(new Point(0, 0, 0))
                .generate();
        var solar = TestUtil.celestialBodyGenerator()
                .withOrbit(TestUtil.orbitGenerator()
                        .withSemiMajorAxis(10)
                        .withEccentricity(0)
                        .withInclination(0)
                        .withLongitudeAscNode(0)
                        .withPerihelionArgument(0)
                        .withTrueAnomaly(0)
                        .generate())
                .generate();
        var intervals = Map.of(
                LongPair.of(0, 10), Math.PI / 2,
                LongPair.of(11, 20), Math.PI,
                LongPair.of(25, 30), - Math.PI / 2);

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
        var spacecraft = TestUtil.spacecraftGenerator()
                .withPosition(new Point(0, 0, 0))
                .generate();
        var solar = TestUtil.celestialBodyGenerator()
                .withOrbit(TestUtil.orbitGenerator()
                        .withSemiMajorAxis(10)
                        .withEccentricity(0)
                        .withInclination(0)
                        .withLongitudeAscNode(0)
                        .withPerihelionArgument(0)
                        .withTrueAnomaly(0)
                        .generate())
                .generate();
        var intervals = Map.of(
                LongPair.of(0, 10), Math.PI / 2,
                LongPair.of(11, 20), Math.PI,
                LongPair.of(25, 30), - Math.PI / 2);

        var profile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> profile.getThrustForceDirection(10));
    }
}