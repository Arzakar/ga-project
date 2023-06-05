package org.klimashin.ga.first.solution.application.controller.v1;

import org.klimashin.ga.first.solution.api.dto.FixedDeviationCommandProfileRequestDtoV1;
import org.klimashin.ga.first.solution.api.dto.InitialStateRequestDtoV1;
import org.klimashin.ga.first.solution.api.resource.SolutionResourceV1;
import org.klimashin.ga.first.solution.application.facade.SolverFacade;
import org.klimashin.ga.first.solution.application.mapper.CommandProfileMapper;
import org.klimashin.ga.first.solution.application.mapper.InitialStateMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SolverControllerV1 implements SolutionResourceV1 {

    CommandProfileMapper commandProfileMapper;
    InitialStateMapper initialStateMapper;
    SolverFacade solverFacade;

    @Override
    public void createInitialState(InitialStateRequestDtoV1 requestDtoV1) {
        var creationData = initialStateMapper.requestDtoV1ToCreationData(requestDtoV1);
        solverFacade.createInitialState(creationData);
    }

    public void loadFixedVectorDeviationCommandProfile(UUID initialStateId, FixedDeviationCommandProfileRequestDtoV1 requestDtoV1) {
        var commandProfileData = commandProfileMapper.requestDtoV1ToData(requestDtoV1);
        solverFacade.loadCommandProfile(initialStateId, commandProfileData);
    }
}
