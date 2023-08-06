package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.repository.InitialStateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateService {

    InitialStateRepository repository;

    public Page<InitialStateEntity> getPageByStatus(InitialStateEntity.InitialStateStatus status, int page, int size) {
        return repository.findByStatus(status, PageRequest.of(page, size));
    }

    @Transactional
    public InitialStateEntity save(InitialStateEntity initialState) {
        return repository.save(initialState);
    }

    public List<InitialStateEntity> saveAll(List<InitialStateEntity> initialStates) {
        return repository.saveAll(initialStates);
    }
}
