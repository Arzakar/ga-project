package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.application.repository.SpacecraftRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpacecraftService {

    SpacecraftRepository repository;

    public SpacecraftEntity getSpacecraft(String spacecraftId) {
        return repository.findById(spacecraftId)
                .orElseThrow();
    }
}
