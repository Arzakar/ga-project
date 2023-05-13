package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "result_state")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultStateEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "initial_state_id", nullable = false)
    InitialStateEntity initialState;

    @Column(name = "spacecraft_position_x", nullable = false)
    Double spacecraftPositionX;

    @Column(name = "spacecraft_position_y", nullable = false)
    Double spacecraftPositionY;

    @Column(name = "spacecraft_position_z", nullable = false)
    Double spacecraftPositionZ;

    @Column(name = "spacecraft_speed_x", nullable = false)
    Double spacecraftSpeedX;

    @Column(name = "spacecraft_speed_y", nullable = false)
    Double spacecraftSpeedY;

    @Column(name = "spacecraft_speed_z", nullable = false)
    Double spacecraftSpeedZ;

    @Column(name = "finish_fuel_mass", nullable = false)
    Double finishFuelMass;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    ResultStatus status;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @OneToMany(fetch = FetchType.EAGER)
    List<ResultStateCelestialBodyPartEntity> celestialBodies;
}
