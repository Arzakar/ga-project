package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;

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

    public Point2D calculatePosition(Orbit orbit) {
        var eccentricAnomaly = calculateEccentricAnomaly(orbit.getTrueAnomaly(), orbit.getEccentricity());

        return calculatePosition(orbit, eccentricAnomaly);
    }

    public Point2D calculatePosition(Orbit orbit, double eccentricAnomaly) {
        var r = orbit.getSemiMajorAxis() * (1 - orbit.getEccentricity() * Math.cos(eccentricAnomaly));
        var x = r * Math.cos(orbit.getTrueAnomaly());
        var y = r * Math.sin(orbit.getTrueAnomaly());
        var newRadiusVector = Vector2D.of(x, y).rotate(orbit.getPerihelionArgument());

        return Point2D.of(newRadiusVector.getX(), newRadiusVector.getY());
    }
}
