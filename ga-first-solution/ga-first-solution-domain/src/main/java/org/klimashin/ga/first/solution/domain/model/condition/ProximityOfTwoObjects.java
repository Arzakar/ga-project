package org.klimashin.ga.first.solution.domain.model.condition;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.util.PointParticles;

public record ProximityOfTwoObjects(PointParticle firstParticle,
                                    PointParticle secondParticle,
                                    double requiredDistance) implements TargetState {

    public boolean isAchieved() {
        return PointParticles.distanceBetween(firstParticle, secondParticle) < requiredDistance;
    }
}
