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
@Table(name = "orbit")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrbitEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @Column(name = "semi_major_axis", nullable = false)
    Double semiMajorAxis;

    @Column(name = "eccentricity", nullable = false)
    Double eccentricity;

    @Column(name = "inclination", nullable = false)
    Double inclination;

    @Column(name = "longitude_asc_node", nullable = false)
    Double longitudeAscNode;

    @Column(name = "perihelion_argument", nullable = false)
    Double perihelionArgument;
}
