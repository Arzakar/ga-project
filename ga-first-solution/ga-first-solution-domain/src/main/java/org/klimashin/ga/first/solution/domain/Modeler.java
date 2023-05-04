package org.klimashin.ga.first.solution.domain;

import org.klimashin.ga.first.solution.domain.util.Physics;
import org.klimashin.ga.first.solution.domain.util.Vectors;

public record Modeler(ModelEnvironment environment) {

    private static final long maxDuration = 15_552_000;

    public ModelEnvironment execute() throws RuntimeException {
        var seconds = 0L;
        var deltaTime = 100L;

        var commandProfile = environment.getCommandProfile();
        var targetState = environment.getTargetState();

        while (seconds < maxDuration) {
            var gravitationForce = Physics.gravitationForce(environment.getSpacecraft(), environment.getCentralBody());
            var thrustForce = environment.getSpacecraft().getFuelMass() > 0
                    ? commandProfile.getThrustForceDirection(seconds).multiply(environment.getSpacecraft().getEngineSystemThrust())
                    : Vectors.zero();

            var sumForce = Vectors.zero()
                    .add(gravitationForce)
                    .add(thrustForce);

            environment.getSpacecraft().changePosition(sumForce, deltaTime);
            environment.getSpacecraft().reduceFuel(deltaTime);

            for (var celestialBody : environment.getCelestialBodies()) {
                celestialBody.move(seconds, deltaTime);
            }

            if (targetState.isAchieved()) {
                return environment;
            }

            seconds += deltaTime;
        }

        throw new RuntimeException(String.format("""
                Длительность перелёта превысила %d секунд. Моделирование закончено
                """, maxDuration));
    }
}
