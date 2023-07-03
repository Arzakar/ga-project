package org.klimashin.ga.first.solution.domain.util;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.math.two.dimension.util.Points2D;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PointParticles {

    public static double distanceBetween(PointParticle firstParticle, PointParticle secondParticle) {
        return Points2D.distanceBetween(firstParticle.getPosition(), secondParticle.getPosition());
    }
}
