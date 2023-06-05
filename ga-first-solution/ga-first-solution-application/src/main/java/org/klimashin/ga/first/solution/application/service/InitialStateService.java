package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.data.initial.state.InitialStateData;
import org.klimashin.ga.first.solution.application.entity.CommandProfileType;
import org.klimashin.ga.first.solution.application.mapper.InitialStateMapper;
import org.klimashin.ga.first.solution.application.repository.InitialStateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateService {

    InitialStateMapper mapper;
    InitialStateRepository repository;

    public InitialStateData findById(UUID initialStateId) {
        var entity = repository.findById(initialStateId)
                .orElseThrow();
        return mapper.entityToData(entity);
    }

    public void save(InitialStateData data) {
        var entity = mapper.dataToEntity(data);
        repository.save(entity);
    }

    public void loadCommandProfile(UUID initialStateId, CommandProfileType commandProfileType, String commandProfilePayload) {
        repository.updateCommandProfile(initialStateId, commandProfileType, commandProfilePayload);
    }
}
