package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "spacecraft")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpacecraftEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @Column(name = "dry_mass", nullable = false)
    Double dryMass;

    @Column(name = "start_fuel_mass", nullable = false)
    Double startFuelMass;

    @OneToOne
    @JoinColumn(name = "engine_id", nullable = false)
    EngineEntity engine;

    @Column(name = "engine_count", nullable = false)
    Integer engineCount;
}
