package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "engine")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EngineEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "thrust", nullable = false)
    Double thrust;

    @Column(name = "fuel_consumption", nullable = false)
    Double fuelConsumption;
}
