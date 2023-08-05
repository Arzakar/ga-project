package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.repository.InitialStateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateService {

    InitialStateRepository repository;

    @Transactional
    public InitialStateEntity save(InitialStateEntity entity) {
        return repository.save(entity);
    }
}
