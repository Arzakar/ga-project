package org.klimashin.ga.first.solution.application.facade;

import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateData;
import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateCreationData;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;
import org.klimashin.ga.first.solution.application.mapper.CommandProfileMapper;
import org.klimashin.ga.first.solution.application.service.CelestialBodyService;
import org.klimashin.ga.first.solution.application.service.InitialStateService;
import org.klimashin.ga.first.solution.application.service.SpacecraftService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SolverFacade {

    CommandProfileMapper commandProfileMapper;
    CelestialBodyService celestialBodyService;
    InitialStateService initialStateService;
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
}
