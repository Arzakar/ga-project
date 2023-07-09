package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.mapper.CelestialBodyMapper;
import org.klimashin.ga.first.solution.application.repository.CelestialBodyRepository;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CelestialBodyService {

    CelestialBodyMapper mapper;
    CelestialBodyRepository repository;

    public CelestialBody getCelestialBody(UUID celestialBodyId) {
        return repository.findById(celestialBodyId)
                .map(mapper::entityToDomain)
                .orElseThrow();
    }
}
