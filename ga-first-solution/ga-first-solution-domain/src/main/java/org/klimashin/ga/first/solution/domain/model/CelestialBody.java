package org.klimashin.ga.first.solution.domain.model;

import org.klimashin.ga.first.solution.domain.method.FixedPointIterationMethod;
import org.klimashin.ga.first.solution.domain.util.Physics;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBody extends PointParticle {

    String name;
    Orbit orbit;

    public CelestialBody move(long currentTime, long deltaTime) {
        var eccentricAnomaly = this.getEccentricAnomaly(currentTime + deltaTime);
        var x = orbit.getSemiMajorAxis() * Math.cos(eccentricAnomaly);
        var y = orbit.getSemiMajorAxis() * Math.sqrt(1 - orbit.getEccentricity()) * Math.sin(eccentricAnomaly);

        this.position.change(x, y, position.getZ());

        return this;
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
        return new FixedPointIterationMethod(initialGuess -> orbit.getEccentricity() * Math.sin(initialGuess) + initialGuess, 0.000001)
                .getSolution(meanAnomaly);
    }
}
