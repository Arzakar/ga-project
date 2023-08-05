package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.SpacecraftInitialStateEntity;
import org.klimashin.ga.first.solution.application.repository.SpacecraftInitialStateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpacecraftInitialStateService {

    SpacecraftInitialStateRepository repository;

    public SpacecraftInitialStateEntity getSpacecraftInitialState(String spacecraftInitialStateId) {
        return repository.findById(spacecraftInitialStateId)
                .orElseThrow();
    }
}
