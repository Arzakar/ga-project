package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.data.SpacecraftData;
import org.klimashin.ga.first.solution.application.mapper.SpacecraftMapper;
import org.klimashin.ga.first.solution.application.repository.SpacecraftRepository;

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

    public SpacecraftData getSpacecraft(UUID spacecraftId) {
        return repository.findById(spacecraftId)
                .map(mapper::entityToData)
                .orElseThrow();
    }
}
