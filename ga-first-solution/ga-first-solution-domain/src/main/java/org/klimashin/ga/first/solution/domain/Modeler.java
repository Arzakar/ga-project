package org.klimashin.ga.first.solution.domain;

import org.klimashin.ga.first.solution.domain.model.exception.NoOptimalSolutionException;
import org.klimashin.ga.first.solution.domain.util.Physics;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.math.util.Points;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Modeler(ModelEnvironment environment) {

    private static final long maxDuration = 31_536_000;

    public ModelEnvironment execute() throws RuntimeException {
        var seconds = 0L;
        var deltaTime = 100L;

        var spacecraft = environment.getSpacecraft();
        var earth = environment.getCelestialBodies().stream()
                .filter(body -> body.getName().equals("Earth"))
                .findFirst()
                .orElseThrow();

        var minDistance = 0d;
        var currentDistance = Points.distanceBetween(spacecraft.getPosition(), earth.getPosition());

        while (seconds < maxDuration) {
            executeStep(seconds, deltaTime);

            var newDistance = Points.distanceBetween(spacecraft.getPosition(), earth.getPosition());
            if (newDistance < currentDistance) {
                minDistance = newDistance;
            }
            currentDistance = newDistance;

            if (environment.getTargetState().isAchieved()) {
                return environment;
            }

            seconds += deltaTime;
        }

        throw new NoOptimalSolutionException(String.format("Длительность перелёта превысила %d секунд", maxDuration), minDistance);
    }

    public List<ModelEnvironment> detailedExecute(final int nodesCount) throws RuntimeException {
        var resultSet = new ArrayList<ModelEnvironment>();

        var seconds = 0L;
        var deltaTime = 100L;

        var iteration = 0;
        var compressionRate = 1;

        while (seconds < maxDuration) {
            executeStep(seconds, deltaTime);

            if (iteration % compressionRate == 0) {
                resultSet.add(environment.copy());

                if (resultSet.size() > nodesCount) {
                    compressionRate *= 2;

                    var compressedResultSet = IntStream.range(0, resultSet.size())
                            .filter(value -> value % 2 == 0)
                            .mapToObj(resultSet::get)
                            .collect(Collectors.toCollection(ArrayList::new));

                    var lastElement = resultSet.get(resultSet.size() - 1);
                    if (!compressedResultSet.contains(lastElement)) {
                        compressedResultSet.add(lastElement);
                    }

                    resultSet = compressedResultSet;
                }
            }

            if (environment.getTargetState().isAchieved()) {
                resultSet.add(environment.copy());
                return resultSet;
            }

            seconds += deltaTime;
            iteration++;
        }

        resultSet.add(environment.copy());
        return resultSet;
    }

    private void executeStep(final long seconds, final long deltaTime) {
        var isEnoughFuel = environment.getSpacecraft().getFuelMass() > 0 && environment.getSpacecraft().isEnoughFuel(deltaTime);

        var gravitationForce = Physics.gravitationForce(environment.getSpacecraft(), environment.getCentralBody());
        var thrustForce = isEnoughFuel
                ? environment.getCommandProfile().getThrustForceDirection(seconds).multiply(environment.getSpacecraft().getEngineSystemThrust())
                : Vector2D.zero();
        var sumForce = Vector2D.zero()
                .add(gravitationForce)
                .add(thrustForce);

        environment.getSpacecraft().move(sumForce, deltaTime);
        environment.getCelestialBodies().forEach(celestialBody -> celestialBody.move(deltaTime));

        if (isEnoughFuel) {
            environment.getSpacecraft().reduceFuel(deltaTime);
        }
    }
}
