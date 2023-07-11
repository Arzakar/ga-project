package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.data.CelestialBodyData;
import org.klimashin.ga.first.solution.application.mapper.CelestialBodyMapper;
import org.klimashin.ga.first.solution.application.repository.CelestialBodyRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CelestialBodyService {

    CelestialBodyMapper mapper;
    CelestialBodyRepository repository;

    public CelestialBodyData getCelestialBody(UUID celestialBodyId) {
        return repository.findById(celestialBodyId)
                .map(mapper::entityToData)
                .orElseThrow();
    }

    public List<CelestialBodyData> findByIds(List<UUID> celestialBodyIds) {
        return repository.findAllById(celestialBodyIds).stream()
                .map(mapper::entityToData)
                .collect(Collectors.toList());
    }
}
