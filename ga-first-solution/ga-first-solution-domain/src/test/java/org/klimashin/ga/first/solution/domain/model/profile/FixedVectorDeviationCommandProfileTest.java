package org.klimashin.ga.first.solution.domain.model.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.klimashin.ga.first.solution.domain.TestUtils;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.util.LongPair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class FixedVectorDeviationCommandProfileTest {

    @Test
    void shouldCorrectConstruct() {
        var firstPair = LongPair.of(0L, 10L);
        var secondPair = LongPair.of(11L, 20L);
        var thirstPair = LongPair.of(25L, 30L);
        var intervals = Map.of(secondPair, 5d, thirstPair, 10d, firstPair, 20d);

        var profile = new FixedVectorDeviationCommandProfile(null, null, intervals);

        assertThat(profile.getIntervals()).hasSize(3);
        assertThat(profile.getIntervals().firstKey()).isEqualTo(firstPair);
        assertThat(profile.getIntervals().lastKey()).isEqualTo(thirstPair);
    }

    @ParameterizedTest
    @MethodSource("thrustForceDirectionTestSource")
    void getThrustForceDirection_shouldReturnVector(long currentTime, Vector2D expectedVector) {
        var spacecraft = TestUtils.spacecraftGenerator()
                .withPosition(Point2D.of(0, 0))
                .generate();
        var solar = TestUtils.celestialBodyGenerator()
                .withOrbit(TestUtils.orbitGenerator()
                        .withSemiMajorAxis(10)
                        .withEccentricity(0)
                        .withInclination(0)
                        .withLongitudeAscNode(0)
                        .withPerihelionArgument(0)
                        .withTrueAnomaly(0)
                        .generate())
                .generate();
        var intervals = Map.of(
                LongPair.of(0L, 10L), Math.PI / 2,
                LongPair.of(11L, 20L), Math.PI,
                LongPair.of(25L, 30L), - Math.PI / 2);

        var profile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);

        assertThat(profile.getThrustForceDirection(currentTime))
                .returns(expectedVector.getX(), Vector2D::getX)
                .returns(expectedVector.getY(), Vector2D::getY);
    }

    private static Stream<Arguments> thrustForceDirectionTestSource() {
        return Stream.of(
                Arguments.of(5, Vector2D.of(0, 1)),
                Arguments.of(15, Vector2D.of(-1, 0)),
                Arguments.of(25, Vector2D.of(0, -1)),
                Arguments.of(45, Vector2D.of(0, -1))
        );
    }

    @Test
    void getThrustForceDirection_shouldThrowException() {
        var spacecraft = TestUtils.spacecraftGenerator()
                .withPosition(Point2D.of(0, 0))
                .generate();
        var solar = TestUtils.celestialBodyGenerator()
                .withOrbit(TestUtils.orbitGenerator()
                        .withSemiMajorAxis(10)
                        .withEccentricity(0)
                        .withInclination(0)
                        .withLongitudeAscNode(0)
                        .withPerihelionArgument(0)
                        .withTrueAnomaly(0)
                        .generate())
                .generate();
        var intervals = Map.of(
                LongPair.of(0L, 10L), Math.PI / 2,
                LongPair.of(11L, 20L), Math.PI,
                LongPair.of(25L, 30L), - Math.PI / 2);

        var profile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> profile.getThrustForceDirection(10));
    }
}