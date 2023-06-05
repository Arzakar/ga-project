package org.klimashin.ga.first.solution.domain;

import org.junit.jupiter.api.Test;
import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;
import org.klimashin.ga.first.solution.domain.model.condition.ProximityOfTwoObjects;
import org.klimashin.ga.first.solution.domain.model.profile.FixedVectorDeviationCommandProfile;
import org.klimashin.ga.first.solution.domain.util.Vectors;

import java.time.Duration;
import java.util.List;
import java.util.Map;

class Demo {

    private ModelEnvironment prepareEnvironment() {
        var solar = new PointParticle(1.9885 * Math.pow(10, 30), Point.of(0, 0, 0));
        var earth = CelestialBody.builder()
                .mass(5.9722 * Math.pow(10, 24))
                .orbit(Orbit.builder()
                        .semiMajorAxis(1.496 * Math.pow(10, 11))
                        .eccentricity(0)
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
                .position(Point.of(earth.getPosition().getX() - Math.pow(10, 9), 0, 0))
                .speed(earth.getSpeed())
                .acceleration(Vectors.zero())
                .fuelMass(100)
                .engine(Engine.builder()
                        .thrust(0.198)
                        .fuelConsumption(0.000010)
                        .build())
                .engineCount(1)
                .build();
        var intervals = Map.of(
                Pair.of(0, Duration.ofDays(50).toSeconds()), Math.toRadians(-62),
                Pair.of(Duration.ofDays(50).toSeconds(), Duration.ofDays(142).toSeconds()), Math.toRadians(52)
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

        var result = modeler.detailedExecute(150);

        result.stream().forEachOrdered(env -> {
            var rate = Math.pow(10, 9);
            var spacecraft = env.getSpacecraft();
            var earth = env.getCelestialBodies().get(0);
            var earthX = earth.getPosition().getX() / rate;
            var earthY = earth.getPosition().getY() / rate;
            var scX = spacecraft.getPosition().getX() / rate;
            var scY = spacecraft.getPosition().getY() / rate;
            var scMass = spacecraft.getMass();
            var trueAnomaly = Math.toDegrees(earth.getOrbit().getTrueAnomaly());
            System.out.println(earthX + "," + earthY + "," + scX + "," + scY + "," + scMass + "," + trueAnomaly);
        });
    }
}
