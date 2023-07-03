package org.klimashin.ga.first.solution.application.facade;

import org.klimashin.ga.first.solution.application.data.PointData;
import org.klimashin.ga.first.solution.application.data.VectorData;
import org.klimashin.ga.first.solution.application.data.condition.ProximityOfTwoObjectsData;
import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateData;
import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateCreationData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;
import org.klimashin.ga.first.solution.application.mapper.CommandProfileMapper;
import org.klimashin.ga.first.solution.application.mapper.OrbitMapper;
import org.klimashin.ga.first.solution.application.service.CelestialBodyService;
import org.klimashin.ga.first.solution.application.service.InitialStateService;
import org.klimashin.ga.first.solution.application.service.SpacecraftService;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.util.Orbits;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SolverFacade {

    CommandProfileMapper commandProfileMapper;
    CelestialBodyService celestialBodyService;
    InitialStateService initialStateService;
    OrbitMapper orbitMapper;
    SpacecraftService spacecraftService;

    public void createInitialState(InitialStateCreationData creationData) {
        var centralBody = celestialBodyService.getCelestialBody(creationData.getCentralBodyId());
        var celestialBodies = creationData.getCelestialBodies().entrySet().stream()
                .map(entry -> {
                    var celestialBodyId = entry.getKey();
                    var trueAnomaly = entry.getValue();
                    var celestialBody = celestialBodyService.getCelestialBody(celestialBodyId);
                    var orbit = celestialBody.getOrbit().setTrueAnomaly(trueAnomaly);

                    return celestialBody.setOrbit(orbit);
                }).toList();
        var spacecraft = spacecraftService.getSpacecraft(creationData.getSpacecraftId())
                .setPosition(creationData.getSpacecraftPosition())
                .setSpeed(creationData.getSpacecraftSpeed());

        var initialState = new InitialStateData()
                .setCentralBody(centralBody)
                .setCelestialBodies(celestialBodies)
                .setSpacecraft(spacecraft)
                .setCommandProfile(creationData.getCommandProfile())
                .setTargetState(creationData.getTargetState());

        initialStateService.save(initialState);
    }

    public void loadCommandProfile(UUID initialStateId, CommandProfileData commandProfile) {
        var commandProfileType = commandProfileMapper.profileDataToProfileTypeEntity(commandProfile);
        var commandProfilePayload = commandProfileMapper.profileDataToProfilePayloadEntity(commandProfile);

        initialStateService.loadCommandProfile(initialStateId, commandProfileType, commandProfilePayload);
    }

    public void generateStates() {
        var centralBodyId = UUID.fromString("e83be6b3-298f-447b-9781-6e0cb8041fcb");
        var celestialBodiesMap = Map.of(UUID.fromString("3aa2e481-e61f-44c4-8b2d-e6a8825d3468"), 0d);
        var spacecraftId = UUID.fromString("2fd64b7f-009f-473f-af79-f18022f2106c");

        var earth = celestialBodyService.getCelestialBody(UUID.fromString("3aa2e481-e61f-44c4-8b2d-e6a8825d3468"));
        var earthOrbit = orbitMapper.dataToDomain(earth.getOrbit());
        var earthPosition = Orbits.calculatePosition(earthOrbit);
        var spacecraftPosition = new PointData()
                .setX(earthPosition.getX() - Math.pow(10, 9))
                .setY(earthPosition.getY())
                .setZ(earthPosition.getZ());
        var spacecraftSpeed = new VectorData()
                .setX(0d)
                .setY(30290d)
                .setZ(0d);

        var centralBody = celestialBodyService.getCelestialBody(centralBodyId);
        var celestialBodies = celestialBodiesMap.entrySet().stream()
                .map(entry -> {
                    var celestialBodyId = entry.getKey();
                    var trueAnomaly = entry.getValue();
                    var celestialBody = celestialBodyService.getCelestialBody(celestialBodyId);
                    var orbit = celestialBody.getOrbit().setTrueAnomaly(trueAnomaly);

                    return celestialBody.setOrbit(orbit);
                }).toList();
        var spacecraft = spacecraftService.getSpacecraft(spacecraftId)
                .setPosition(spacecraftPosition)
                .setSpeed(spacecraftSpeed);
        var targetState = new ProximityOfTwoObjectsData()
                .setFirstObjectId(spacecraft.getId())
                .setSecondObjectId(celestialBodies.get(0).getId())
                .setRequiredDistance(Math.pow(10, 9));

        var leftBoundForDuration = Duration.ofDays(10).toSeconds();
        var rightBoundForDuration = Duration.ofDays(180).toSeconds();
        var durationStep = Duration.ofDays(10).toSeconds();
        var intervalCount = 2;

        var leftBoundForDeviation = Math.toRadians(-180);
        var rightBoundForDeviation = Math.toRadians(180);
        var deviationStep = Math.toRadians(10);

        var durationRange = LongStream.concat(
                LongStream.iterate(leftBoundForDuration, value -> value <= rightBoundForDuration, value -> value + durationStep),
                LongStream.of(rightBoundForDuration)
        ).distinct().sorted().toArray();
        var deviationRange = DoubleStream.concat(
                DoubleStream.iterate(leftBoundForDeviation, value -> value <= rightBoundForDeviation, value -> value + deviationStep),
                DoubleStream.of(rightBoundForDuration)
        ).distinct().sorted().toArray();

        Pair<Long, Double>[] sectorVariations = Arrays.stream(durationRange)
                .mapToObj(duration -> Arrays.stream(deviationRange)
                        .mapToObj(deviation -> new Pair<>(duration, deviation))
                        .toArray((IntFunction<Pair<Long, Double>[]>) Pair[]::new))
                .flatMap(Arrays::stream)
                .toArray((IntFunction<Pair<Long, Double>[]>) Pair[]::new);

        for (int fpDuration = 0; fpDuration < durationRange.length; fpDuration++) {
            for (int spDuration = 0; spDuration < durationRange.length; spDuration++) {
                for (int fpDeviation = 0; fpDeviation < deviationRange.length; fpDeviation++) {
                    for (int spDeviation = 0; spDeviation < deviationRange.length; spDeviation++) {
//
//                        var commandProfile = new FixedVectorDeviationProfileData()
//                                .setStartVectorObjectId(spacecraftId)
//                                .setEndVectorObjectId(centralBodyId)
//                                .setIntervals(Map.of(
//                                        Pair.of(0, durationRange[fpDuration]), deviationRange[fpDeviation],
//                                        Pair.of(durationRange[fpDuration] + durationRange[fpDuration])
//                                ));
//                        var initialState = new InitialStateData()
//                                .setCentralBody(centralBody)
//                                .setCelestialBodies(celestialBodies)
//                                .setSpacecraft(spacecraft)
//                                .setCommandProfile(creationData.getCommandProfile())
//                                .setTargetState(targetState);
//
//                        initialStateService.save(initialState);
                    }
                }
            }
        }


    }
}
