package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.IntegrationTest;
import org.klimashin.ga.first.solution.application.data.ModelEnvironmentData;
import org.klimashin.ga.first.solution.application.data.condition.ProximityOfTwoObjectsData;
import org.klimashin.ga.first.solution.application.data.profile.FixedVectorDeviationProfile;
import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.application.entity.EngineEntity;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@IntegrationTest
class ModelEnvironmentMapperTest {

    private final EasyRandom easyRandom = defaultEasyRandom();

    @Autowired
    private ModelEnvironmentMapper mapper;

    @Test
    void dataToInitialStateEntity_shouldMap() {
        var data = easyRandom.nextObject(ModelEnvironmentData.class)
                .setCommandProfile(new FixedVectorDeviationProfile()
                        .setStartVectorObject(new PointParticle(100, Point.of(1, 1, 1)))
                        .setEndVectorObject(new PointParticle(200, Point.of(2, 2, 2)))
                        .setIntervals(Map.of(Pair.of(10, 20), 500d))
                        .setTimeBounds(List.of(Pair.of(10, 20))))
                .setTargetState(new ProximityOfTwoObjectsData()
                        .setFirstParticle(new PointParticle(100, Point.of(1, 1, 1)))
                        .setSecondParticle(new PointParticle(200, Point.of(2, 2, 2)))
                        .setRequiredDistance(500d));
        var centralBody = data.getCentralBody();
        var centralBodyOrbit = data.getCentralBody().getOrbit();
        var spacecraft = data.getSpacecraft();
        var engine = data.getSpacecraft().getEngine();
        var commandProfile = data.getCommandProfile();
        var targetState = data.getTargetState();

        var expectedCommandProfilePayload = """
                {
                    "startVectorObject": {
                        "mass": 100.0,
                        "position": {
                            "x": 1.0,
                            "y": 1.0,
                            "z": 1.0
                        }
                    },
                    "endVectorObject": {
                        "mass":200.0,
                        "position": {
                            "x": 2.0,
                            "y": 2.0,
                            "z": 2.0
                        }
                    },
                    "intervals": {
                        "Pair[leftValue=10, rightValue=20]": 500.0
                    },
                    "timeBounds": [
                        {
                            "leftValue": 10,
                            "rightValue": 20
                        }
                    ]
                }
                """;
        var expectedTargetStatePayload = """
                {
                    "firstParticle": {
                        "mass": 100.0,
                        "position": {
                            "x": 1.0,
                            "y": 1.0,
                            "z": 1.0
                        }
                    },
                    "secondParticle": {
                        "mass": 200.0,
                        "position": {
                            "x": 2.0,
                            "y": 2.0,
                            "z": 2.0
                        }
                    },
                    "requiredDistance":500.0
                }
                """;

        var result = mapper.dataToInitialStateEntity(data);

        assertThat(result)
                .returns(null, InitialStateEntity::getId)
                .returns(null, InitialStateEntity::getResultState)
                .returns(null, InitialStateEntity::getCelestialBodies);

        assertThat(result.getCentralBody())
                .returns(centralBody.getId(), CelestialBodyEntity::getId)
                .returns(centralBody.getName(), CelestialBodyEntity::getName)
                .returns(centralBody.getMass(), CelestialBodyEntity::getMass)
                .extracting(CelestialBodyEntity::getOrbit)
                .returns(centralBodyOrbit.getId(), OrbitEntity::getId)
                .returns(centralBodyOrbit.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(centralBodyOrbit.getEccentricity(), OrbitEntity::getEccentricity)
                .returns(centralBodyOrbit.getInclination(), OrbitEntity::getInclination)
                .returns(centralBodyOrbit.getLongitudeAscNode(), OrbitEntity::getLongitudeAscNode)
                .returns(centralBodyOrbit.getPerihelionArgument(), OrbitEntity::getPerihelionArgument);

        assertThat(result)
                .returns(spacecraft.getPosition().getX(), InitialStateEntity::getSpacecraftPositionX)
                .returns(spacecraft.getPosition().getY(), InitialStateEntity::getSpacecraftPositionY)
                .returns(spacecraft.getPosition().getZ(), InitialStateEntity::getSpacecraftPositionZ)
                .returns(spacecraft.getSpeed().getX(), InitialStateEntity::getSpacecraftSpeedX)
                .returns(spacecraft.getSpeed().getY(), InitialStateEntity::getSpacecraftSpeedY)
                .returns(spacecraft.getSpeed().getZ(), InitialStateEntity::getSpacecraftSpeedZ)
                .extracting(InitialStateEntity::getSpacecraft)
                .returns(spacecraft.getId(), SpacecraftEntity::getId)
                .returns(spacecraft.getDryMass(), SpacecraftEntity::getDryMass)
                .returns(spacecraft.getFuelMass(), SpacecraftEntity::getStartFuelMass)
                .returns(spacecraft.getEngineCount(), SpacecraftEntity::getEngineCount)
                .extracting(SpacecraftEntity::getEngine)
                .returns(engine.getId(), EngineEntity::getId)
                .returns(engine.getName(), EngineEntity::getName)
                .returns(engine.getThrust(), EngineEntity::getThrust)
                .returns(engine.getFuelConsumption(), EngineEntity::getFuelConsumption);

        assertThat(result)
                .returns(commandProfile.getType().name(), state -> state.getCommandProfileType().name())
                .returns(targetState.getType().name(), state -> state.getTargetStateType().name());

        assertThat(result.getCommandProfilePayload()).isEqualToIgnoringWhitespace(expectedCommandProfilePayload);
        assertThat(result.getTargetStatePayload()).isEqualToIgnoringWhitespace(expectedTargetStatePayload);
    }
}