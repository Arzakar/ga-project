package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Orbits {

    public double extractEccentricAnomaly(Orbit orbit) {
        return calculateEccentricAnomaly(orbit.getTrueAnomaly(), orbit.getEccentricity());
    }

    public double calculateEccentricAnomaly(double trueAnomaly, double eccentricity) {
        double rate = Math.sqrt((1 - eccentricity) / (1 + eccentricity));
        return Math.atan(rate * Math.tan(trueAnomaly / 2)) * 2;
    }

    public double calculateTrueAnomaly(double eccentricAnomaly, double eccentricity) {
        double rate = Math.sqrt((1 + eccentricity) / (1 - eccentricity));
        return Math.atan(rate * Math.tan(eccentricAnomaly / 2)) * 2;
    }

    public Point calculatePosition(Orbit orbit) {
        var eccentricAnomaly = calculateEccentricAnomaly(orbit.getTrueAnomaly(), orbit.getEccentricity());

        var x = orbit.getSemiMajorAxis() * (Math.cos(eccentricAnomaly) - orbit.getEccentricity());
        var y = orbit.getSemiMajorAxis() * Math.sqrt(1 - Math.pow(orbit.getEccentricity(), 2)) * Math.sin(eccentricAnomaly);

        return Vector.of(x, y, 0)
                .rotateByZ(orbit.getPerihelionArgument())
                .toPoint();
    }

    public Point calculatePosition(Orbit orbit, double eccentricAnomaly) {
        var x = orbit.getSemiMajorAxis() * (Math.cos(eccentricAnomaly) - orbit.getEccentricity());
        var y = orbit.getSemiMajorAxis() * Math.sqrt(1 - Math.pow(orbit.getEccentricity(), 2)) * Math.sin(eccentricAnomaly);

        return Vector.of(x, y, 0)
                .rotateByZ(orbit.getPerihelionArgument())
                .toPoint();
    }
}
