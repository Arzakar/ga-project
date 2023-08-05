package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.regex.Pattern;

@Entity
@Table(name = "spacecraft")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpacecraftEntity {

    @Id
    @Column(name = "id", nullable = false)
    String id;

    @Column(name = "dry_mass", nullable = false)
    Double dryMass;

    @Column(name = "start_fuel_mass", nullable = false)
    Double startFuelMass;

    @Column(name = "engine_thrust", nullable = false)
    Double engineThrust;

    @Column(name = "engine_fuel_consumption", nullable = false)
    Double engineFuelConsumption;

    @Column(name = "engine_count", nullable = false)
    Integer engineCount;

    public SpacecraftEntity(String id, double dryMass, double startFuelMass, double engineThrust, double engineFuelConsumption, int engineCount) {

        var idIsValid = Pattern.compile("Mass\\d{2,10}Thr\\d{1,10}").matcher(id).find();

        if (!idIsValid) {
            throw new RuntimeException("Invalid id format");
        }

        this.id = id;
        this.dryMass = dryMass;
        this.startFuelMass = startFuelMass;
        this.engineThrust = engineThrust;
        this.engineFuelConsumption = engineFuelConsumption;
        this.engineCount = engineCount;
    }
}
