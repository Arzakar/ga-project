package org.klimashin.ga.first.solution.domain;

import org.junit.jupiter.api.Test;

import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;
import org.klimashin.ga.first.solution.domain.model.condition.ProximityOfTwoObjects;
import org.klimashin.ga.first.solution.domain.model.profile.FixedVectorDeviationCommandProfile;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.math.util.Points;
import org.klimashin.ga.first.solution.util.util.LongPair;

import java.time.Duration;
import java.util.List;
import java.util.Map;

class DemoTest {

    private ModelEnvironment prepareEnvironment() {
        var solar = new PointParticle(1.9885E+30, Point2D.of(0, 0));
        var earth = CelestialBody.builder()
                .mass(5.9722E+24)
                .orbit(Orbit.builder()
                        .apocenter(1.521E+11)
                        .pericenter(1.471E+11)
                        .semiMajorAxis(1.496E+11)
                        .eccentricity(0.0167086)
                        .inclination(0)
                        .longitudeAscNode(0)
                        .perihelionArgument(0)
                        .trueAnomaly(0)
                        .attractingBodyMass(solar.getMass())
                        .zeroEpoch(0)
                        .build())
                .name("Earth")
                .build();
        var spacecraft = Spacecraft.builder()
                .mass(350)
                .position(Point2D.of(earth.getPosition().getX() - Math.pow(10, 9), 0))
                .speed(Vector2D.of(0, 29784))
                .fuelMass(100)
                .engine(Engine.builder()
                        .thrust(0.220)
                        .fuelConsumption(0.000010)
                        .build())
                .engineCount(1)
                .build();
        var intervals = Map.of(
                LongPair.of(0L, Duration.ofDays(15).toSeconds()), Math.toRadians(-40),
                LongPair.of(Duration.ofDays(15).toSeconds(), Duration.ofDays(105).toSeconds()), Math.toRadians(160)
        );
        var commandProfile = new FixedVectorDeviationCommandProfile(spacecraft, solar, intervals);
        var targetState = new ProximityOfTwoObjects(spacecraft, earth, Math.pow(10, 9));

        return ModelEnvironment.builder()
                .centralBody(solar)
                .celestialBodies(List.of(earth))
                .spacecraft(spacecraft)
                .commandProfile(commandProfile)
                .targetState(targetState)
                .build();
    }

    @Test
    void demo() {
        var environment = prepareEnvironment();
        var modeler = new Modeler(environment);

        var result = modeler.execute();

        System.out.println(result);
    }

    @Test
    void demoDetailed() {
        var environment = prepareEnvironment();
        var modeler = new Modeler(environment);

        var result = modeler.detailedExecute(500);

        var numberFormat = "%.3e";

        result.stream().forEachOrdered(env -> {
            var rate = 1;
            var spacecraft = env.getSpacecraft();
            var earth = env.getCelestialBodies().get(0);
            var earthX = String.format(numberFormat, earth.getPosition().getX() / rate);
            var earthY = String.format(numberFormat, earth.getPosition().getY() / rate);
            var scX = String.format(numberFormat, spacecraft.getPosition().getX() / rate);
            var scY = String.format(numberFormat, spacecraft.getPosition().getY() / rate);
            var scV = String.format(numberFormat, spacecraft.getSpeed().getScalar());
            var scMass = String.format(numberFormat, spacecraft.getMass());
            var trueAnomaly = String.format("%.3f", Math.toDegrees(earth.getOrbit().getTrueAnomaly()));
            var distance = String.format(numberFormat, Points.distanceBetween(env.getCelestialBodies().get(0).getPosition(), env.getSpacecraft().getPosition()) / rate);
            System.out.println(
                    String.format("%10s", earthX) + "   |"
                    + String.format("%10s", earthY) + "   |"
                    + String.format("%10s", scX) + "   |"
                    + String.format("%10s", scY) + "   |"
                    + String.format("%10s", distance) + "   |"
                    + String.format("%10s", trueAnomaly));
        });
    }
}
