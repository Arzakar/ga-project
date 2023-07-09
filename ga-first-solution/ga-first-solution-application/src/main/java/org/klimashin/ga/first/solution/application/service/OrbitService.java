package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.mapper.OrbitMapper;
import org.klimashin.ga.first.solution.application.repository.OrbitRepository;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrbitService {

    OrbitMapper mapper;
    OrbitRepository repository;

    public Orbit getOrbit(UUID orbitId) {
        return repository.findById(orbitId)
                .map(mapper::entityToDomain)
                .orElseThrow();
    }
}
