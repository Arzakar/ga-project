package org.klimashin.ga.first.solution.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.klimashin.ga.first.solution.domain.TestUtils.testRound;

import org.klimashin.ga.first.solution.domain.TestUtils;

import org.junit.jupiter.api.Test;

class CelestialBodyTest {

    @Test
    void move_shouldMove() {
        var orbit = TestUtils.orbitGenerator()
                .withSemiMajorAxis(50d)
                .withEccentricity(0.3)
                .withPerihelionArgument(0)
                .withTrueAnomaly(Math.PI / 2)
                .withAttractingBodyMass(1000d)
                .withZeroEpoch(0)
                .generate();

        var celestialBody = TestUtils.celestialBodyGenerator()
                .withMass(100d)
                .withOrbit(orbit)
                .generate();

        var result = celestialBody.move(100);

        var precision = 9;
        assertThat(result)
                .isSameAs(celestialBody)
                .extracting(CelestialBody::getPosition)
                .returns(-0.004_015_477, value -> testRound(value.getX(), precision))
                .returns(45.501_204_466, value -> testRound(value.getY(), precision));

        assertThat(result)
                .extracting(CelestialBody::getOrbit)
                .returns(1.570_884_577, value -> testRound(value.getTrueAnomaly(), precision));
    }

    @Test
    void getMeanMotion_shouldReturnMeanMotion() {
        var orbit = TestUtils.orbitGenerator()
                .withAttractingBodyMass(1000d)
                .withSemiMajorAxis(50d)
                .generate();

        var celestialBody = TestUtils.celestialBodyGenerator()
                .withMass(100d)
                .withOrbit(orbit)
                .generate();

        var result = celestialBody.getMeanMotion();

        assertThat(result).isEqualTo(0.000_000_766_380_062, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void getMeanAnomaly_shouldReturnMeanAnomaly() {
        var orbit = TestUtils.orbitGenerator()
                .withAttractingBodyMass(1000d)
                .withSemiMajorAxis(50d)
                .withZeroEpoch(0)
                .generate();

        var celestialBody = TestUtils.celestialBodyGenerator()
                .withMass(100d)
                .withOrbit(orbit)
                .generate();

        var result = celestialBody.getMeanAnomaly(5000);

        assertThat(result).isEqualTo(0.003_831_900_311_856, withPrecision(0.000_000_000_000_001));
    }

    @Test
    void getEccentricAnomaly_shouldReturnEccentricAnomaly() {
        var orbit = TestUtils.orbitGenerator()
                .withSemiMajorAxis(50d)
                .withEccentricity(0.3)
                .withAttractingBodyMass(1000d)
                .withZeroEpoch(0)
                .generate();

        var celestialBody = TestUtils.celestialBodyGenerator()
                .withMass(100d)
                .withOrbit(orbit)
                .generate();

        var result = celestialBody.getEccentricAnomaly(5000);

        assertThat(result).isEqualTo(0.005_473_772, withPrecision(0.000_000_001));
    }
}