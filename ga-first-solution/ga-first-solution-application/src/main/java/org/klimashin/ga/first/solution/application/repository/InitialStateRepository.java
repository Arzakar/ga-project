package org.klimashin.ga.first.solution.application.repository;

import org.klimashin.ga.first.solution.application.entity.CommandProfileType;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface InitialStateRepository extends JpaRepository<InitialStateEntity, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE InitialStateEntity SET "
            + "commandProfileType = :type, "
            + "commandProfilePayload = :payload "
            + "WHERE id = :id")
    void updateCommandProfile(@Param("id") UUID initialStateId,
                              @Param("type") CommandProfileType commandProfileType,
                              @Param("payload") String commandProfilePayload);
}
