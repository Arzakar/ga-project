package org.klimashin.ga.first.solution.domain;

import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.With;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;

@UtilityClass
public class TestUtils {

    private final EasyRandom easyRandom = new EasyRandom();

    public static double testRound(double value, int precision) {
        var rate = Math.pow(10, precision);
        return Math.round(value * rate) / rate;
    }

    public CelestialBodyGenerator celestialBodyGenerator() {
        var mass = easyRandom.nextDouble(100d);

        return CelestialBodyGenerator.builder()
                .mass(mass)
                .orbit(easyRandom.nextObject(Orbit.class))
                .name(easyRandom.nextObject(String.class))
                .build();
    }

    public OrbitGenerator orbitGenerator() {
        return OrbitGenerator.builder()
                .semiMajorAxis(easyRandom.nextDouble(100000d))
                .eccentricity(easyRandom.nextDouble(1d))
                .inclination(easyRandom.nextDouble(0, Math.PI))
                .longitudeAscNode(easyRandom.nextDouble(2 * Math.PI))
                .perihelionArgument(easyRandom.nextDouble(2 * Math.PI))
                .trueAnomaly(easyRandom.nextDouble(2 * Math.PI))
                .attractingBodyMass(easyRandom.nextDouble(100000d))
                .zeroEpoch(0)
                .build();
    }

    public SpacecraftGenerator spacecraftGenerator() {
        var mass = easyRandom.nextDouble(100d);

        return SpacecraftGenerator.builder()
                .mass(mass)
                .position(easyRandom.nextObject(Point2D.class))
                .speed(easyRandom.nextObject(Vector2D.class))
                .fuelMass(mass / 2)
                .engine(Engine.builder()
                        .thrust(easyRandom.nextDouble(0, 100d))
                        .fuelConsumption(easyRandom.nextDouble(0, 100d))
                        .build())
                .engineCount(easyRandom.nextInt(0, 100))
                .build();
    }

    @With
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CelestialBodyGenerator {

        double mass;
        Orbit orbit;
        String name;

        public CelestialBody generate() {
            return CelestialBody.builder()
                    .mass(this.mass)
                    .orbit(this.orbit)
                    .name(this.name)
                    .build();
        }
    }

    @With
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OrbitGenerator {

        double semiMajorAxis;
        double eccentricity;
        double inclination;
        double longitudeAscNode;
        double perihelionArgument;
        double trueAnomaly;
        double attractingBodyMass;
        long zeroEpoch;

        public Orbit generate() {
            return Orbit.builder()
                    .semiMajorAxis(this.semiMajorAxis)
                    .eccentricity(this.eccentricity)
                    .inclination(this.inclination)
                    .longitudeAscNode(this.longitudeAscNode)
                    .perihelionArgument(this.perihelionArgument)
                    .trueAnomaly(this.trueAnomaly)
                    .attractingBodyMass(this.attractingBodyMass)
                    .zeroEpoch(this.zeroEpoch)
                    .build();
        }
    }

    @With
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SpacecraftGenerator {

        double mass;
        Point2D position;
        Vector2D speed;
        Vector2D acceleration;
        double fuelMass;
        Engine engine;
        int engineCount;

        public Spacecraft generate() {
            return Spacecraft.builder()
                    .mass(this.mass)
                    .position(this.position)
                    .speed(this.speed)
                    .fuelMass(this.fuelMass)
                    .engine(this.engine)
                    .engineCount(this.engineCount)
                    .build();
        }
    }
}
