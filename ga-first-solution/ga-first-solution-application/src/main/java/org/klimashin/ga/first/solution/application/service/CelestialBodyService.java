package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.application.repository.CelestialBodyRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CelestialBodyService {

    CelestialBodyRepository repository;

    public CelestialBodyEntity getCelestialBody(String celestialBodyId) {
        return repository.findById(celestialBodyId)
                .orElseThrow();
    }
}
