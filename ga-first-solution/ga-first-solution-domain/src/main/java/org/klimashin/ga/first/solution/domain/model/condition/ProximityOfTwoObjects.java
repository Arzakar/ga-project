package org.klimashin.ga.first.solution.domain.model.condition;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.util.math.util.Points;

public record ProximityOfTwoObjects(PointParticle firstParticle,
                                    PointParticle secondParticle,
                                    double requiredDistance) implements TargetState {

    public boolean isAchieved() {
        return Points.distanceBetween(firstParticle.getPosition(), secondParticle.getPosition()) < requiredDistance;
    }
}
