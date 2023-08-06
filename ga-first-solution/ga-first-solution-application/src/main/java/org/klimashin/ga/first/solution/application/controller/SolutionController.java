package org.klimashin.ga.first.solution.application.controller;

import org.klimashin.ga.first.solution.api.resource.SolutionResourceV1;
import org.klimashin.ga.first.solution.application.facade.SolverFacade;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SolutionController implements SolutionResourceV1 {

    SolverFacade solverFacade;

    @Override
    public void startResolveProcess() {
        solverFacade.startResolveProcess();
    }
}
