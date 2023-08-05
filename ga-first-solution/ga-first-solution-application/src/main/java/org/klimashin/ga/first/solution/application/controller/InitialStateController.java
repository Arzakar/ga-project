package org.klimashin.ga.first.solution.application.controller;

import org.klimashin.ga.first.solution.api.dto.InitialStateGeneratorRequestDtoV1;
import org.klimashin.ga.first.solution.api.resource.InitialStateResourceV1;
import org.klimashin.ga.first.solution.application.facade.InitialStateFacade;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateController implements InitialStateResourceV1 {

    InitialStateFacade initialStateFacade;

    @Override
    public void generateInitialState(InitialStateGeneratorRequestDtoV1 requestDtoV1) {
        initialStateFacade.generateInitialStates(requestDtoV1);
    }
}
