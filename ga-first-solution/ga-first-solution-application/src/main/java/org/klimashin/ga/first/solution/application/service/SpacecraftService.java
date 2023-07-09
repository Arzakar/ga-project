package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.mapper.SpacecraftMapper;
import org.klimashin.ga.first.solution.application.repository.SpacecraftRepository;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpacecraftService {

    SpacecraftMapper mapper;
    SpacecraftRepository repository;

    public Spacecraft getSpacecraft(UUID spacecraftId) {
        return repository.findById(spacecraftId)
                .map(mapper::entityToDomain)
                .orElseThrow();
    }
}
