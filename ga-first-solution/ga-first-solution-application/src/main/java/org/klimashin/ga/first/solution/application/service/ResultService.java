package org.klimashin.ga.first.solution.application.service;

import org.klimashin.ga.first.solution.application.entity.ResultEntity;
import org.klimashin.ga.first.solution.application.repository.ResultRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResultService {

    ResultRepository repository;

    public List<ResultEntity> saveAll(List<ResultEntity> results) {
        return repository.saveAll(results);
    }
}
