package org.klimashin.ga.first.solution.application.facade;

import org.klimashin.ga.first.solution.application.data.InitialStateGeneratorParameters;
import org.klimashin.ga.first.solution.application.service.CelestialBodyService;
import org.klimashin.ga.first.solution.application.service.SpacecraftService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Component;

import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateFacade {

    CelestialBodyService celestialBodyService;
    SpacecraftService spacecraftService;

    public void generateInitialStates(InitialStateGeneratorParameters parameters) {
        var centralBodyData = celestialBodyService.getCelestialBody(parameters.getCentralBodyId());
        var planetsData = parameters.getCelestialBodies().stream()
                .map(bodyParam -> {
                    var body = celestialBodyService.getCelestialBody(bodyParam.getId());
                    body.getOrbit().setTrueAnomaly(bodyParam.getTrueAnomaly());
                    return body;
                })
                .toList();
        var spacecraftData = spacecraftService.getSpacecraft(parameters.getSpacecraft().getId())
                .setPosition(parameters.getSpacecraft().getSpacecraftPosition())
                .setSpeed(parameters.getSpacecraft().getSpacecraftSpeed());

        var durationBounds = parameters.getDurationBounds();
        var durationBoundStep = parameters.getDurationBoundStep();
        var durationList = Stream.concat(
                        LongStream.iterate(durationBounds.getLeft(), i -> i < durationBounds.getRight(), i -> i + durationBoundStep).boxed(),
                        LongStream.of(durationBounds.getRight()).boxed())
                .sorted(Long::compareTo)
                .toList();

        var deviationBounds = parameters.getDeviationBounds();
        var deviationBoundStep = parameters.getDeviationBoundStep();
        var deviationList = Stream.concat(
                        DoubleStream.iterate(deviationBounds.getLeft(), i -> i < deviationBounds.getRight(), i -> i + deviationBoundStep).boxed(),
                        DoubleStream.of(deviationBounds.getRight()).boxed())
                .sorted(Double::compareTo)
                .toList();
    }
}
