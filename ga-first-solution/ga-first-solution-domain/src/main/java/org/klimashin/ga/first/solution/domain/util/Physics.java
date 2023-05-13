package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.math.Vector;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Physics {

    public static final double G = 6.67430 * Math.pow(10, -11);

    public static double gravitationParameter(double mass) {
        return G * mass;
    }

    public static double gravitationParameter(double firstMass, double secondMass) {
        return G * (firstMass + secondMass);
    }

    public static double gravitationForceMagnitude(PointParticle firstParticle, PointParticle secondParticle) {
        var distance = Points.distanceBetween(firstParticle, secondParticle);
        return G * ((firstParticle.getMass() * secondParticle.getMass()) / Math.pow(distance, 2));
    }

    public static Vector gravitationForce(PointParticle gravitatingBody, PointParticle attractiveBody) {
        return Vectors.between(gravitatingBody, attractiveBody)
                .toUnit()
                .multiply(Physics.gravitationForceMagnitude(gravitatingBody, attractiveBody));
    }
}
