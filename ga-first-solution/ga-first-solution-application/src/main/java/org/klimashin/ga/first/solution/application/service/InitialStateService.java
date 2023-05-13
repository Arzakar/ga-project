package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.data.InitialStateData;
import org.klimashin.ga.first.solution.application.repository.InitialStateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateService {

    InitialStateRepository repository;

    public InitialStateData saveInitialState(InitialStateData data) {
        return new InitialStateData();
    }
}
