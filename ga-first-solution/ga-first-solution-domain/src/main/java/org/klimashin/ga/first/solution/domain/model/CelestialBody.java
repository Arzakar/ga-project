package org.klimashin.ga.first.solution.domain.model;

import static org.klimashin.ga.first.solution.domain.util.Physics.G;


import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.method.FixedPointIterationMethod;
import org.klimashin.ga.first.solution.domain.util.Orbits;
import org.klimashin.ga.first.solution.domain.util.Physics;
import org.klimashin.ga.first.solution.math.two.dimension.model.Point2D;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CelestialBody extends PointParticle {

    Orbit orbit;
    String name;

    public static CelestialBodyBuilder builder() {
        return new CelestialBodyBuilder();
    }

    private CelestialBody(double mass, Point2D position, Orbit orbit, String name) {
        this.mass = mass;
        this.position = position;
        this.orbit = orbit;
        this.name = name;
    }

    public CelestialBody move(long deltaTime) {
        var meanMotion = getMeanMotion();
        var eccentricity = orbit.getEccentricity();

        var currentEccentricAnomaly = Orbits.extractEccentricAnomaly(orbit);
        var timeFromPerihelionEpoch = Math.round((currentEccentricAnomaly - eccentricity * Math.sin(currentEccentricAnomaly)) / meanMotion);

        var nextEccentricAnomaly = getEccentricAnomaly(timeFromPerihelionEpoch + deltaTime);
        var nextTrueAnomaly = Orbits.calculateTrueAnomaly(nextEccentricAnomaly, eccentricity);
        var nextPosition = Orbits.calculatePosition(this.orbit, nextEccentricAnomaly);

        orbit.setTrueAnomaly(nextTrueAnomaly);
        position.change(nextPosition);

        return this;
    }

    public Vector getSpeed() {
        var rate = Math.sqrt(G * orbit.getAttractingBodyMass() / orbit.getFocalParameter());
        var radialSpeed = rate * orbit.getEccentricity() * Math.sin(orbit.getTrueAnomaly());
        var transversalSpeed = rate * (1 + orbit.getEccentricity() * Math.cos(orbit.getTrueAnomaly()));

        return Vector.of(radialSpeed, transversalSpeed, 0)
                .rotateByZ(orbit.getTrueAnomaly());
    }

    public double getMeanMotion() {
        return Math.sqrt(Physics.gravitationParameter(mass, orbit.getAttractingBodyMass())
                / Math.pow(orbit.getSemiMajorAxis(), 3));
    }

    public double getMeanAnomaly(long currentTime) {
        return getMeanMotion() * (currentTime - orbit.getZeroEpoch());
    }

    public double getEccentricAnomaly(long currentTime) {
        if (currentTime == orbit.getZeroEpoch()) {
            return 0;
        }

        var meanAnomaly = getMeanAnomaly(currentTime);
        return new FixedPointIterationMethod(variable -> orbit.getEccentricity() * Math.sin(variable) + meanAnomaly, 0.000001)
                .getSolution(meanAnomaly);
    }

    @Setter
    @Accessors(chain = true, fluent = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CelestialBodyBuilder {

        double mass;
        Orbit orbit;
        String name;

        public CelestialBody build() {
            var eccentricAnomaly = Orbits.extractEccentricAnomaly(orbit);
            var position = Orbits.calculatePosition(orbit, eccentricAnomaly);

            return new CelestialBody(mass, position, orbit, name);
        }
    }
}
