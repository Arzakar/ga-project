package org.klimashin.ga.first.solution.domain;

import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;
import org.klimashin.ga.first.solution.domain.model.condition.TargetState;
import org.klimashin.ga.first.solution.domain.model.profile.CommandProfile;
import org.klimashin.ga.first.solution.domain.util.PointParticles;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@ToString
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelEnvironment {

    PointParticle centralBody;
    List<CelestialBody> celestialBodies;
    Spacecraft spacecraft;

    CommandProfile commandProfile;
    TargetState targetState;

    public ModelEnvironment copy() {
        return ModelEnvironment.builder()
                .centralBody(new PointParticle(centralBody.getMass(), PointParticles.copy(centralBody.getPosition())))
                .celestialBodies(celestialBodies.stream()
                        .map(celestialBody -> CelestialBody.builder()
                                .mass(celestialBody.getMass())
                                .orbit(Orbit.builder()
                                        .semiMajorAxis(celestialBody.getOrbit().getSemiMajorAxis())
                                        .eccentricity(celestialBody.getOrbit().getEccentricity())
                                        .inclination(celestialBody.getOrbit().getInclination())
                                        .longitudeAscNode(celestialBody.getOrbit().getLongitudeAscNode())
                                        .perihelionArgument(celestialBody.getOrbit().getPerihelionArgument())
                                        .trueAnomaly(celestialBody.getOrbit().getTrueAnomaly())
                                        .attractingBodyMass(celestialBody.getOrbit().getAttractingBodyMass())
                                        .zeroEpoch(celestialBody.getOrbit().getZeroEpoch())
                                        .build())
                                .name(celestialBody.getName())
                                .build())
                        .toList())
                .spacecraft(Spacecraft.builder()
                        .mass(spacecraft.getMass())
                        .position(spacecraft.getPosition().copy())
                        .speed(spacecraft.getSpeed().copy())
                        .acceleration(spacecraft.getAcceleration().copy())
                        .fuelMass(spacecraft.getFuelMass())
                        .engine(Engine.builder()
                                .thrust(spacecraft.getEngine().getThrust())
                                .fuelConsumption(spacecraft.getEngine().getFuelConsumption())
                                .build())
                        .engineCount(spacecraft.getEngineCount())
                        .build())
                .build();
    }
}
