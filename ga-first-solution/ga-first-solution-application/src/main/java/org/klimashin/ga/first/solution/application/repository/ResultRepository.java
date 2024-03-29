package org.klimashin.ga.first.solution.application.repository;

import org.klimashin.ga.first.solution.application.entity.ResultEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, UUID> {
}
