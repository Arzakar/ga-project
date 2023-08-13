package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "celestial_body")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CelestialBodyEntity {

    @Id
    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "mass", nullable = false)
    Double mass;

    @Column(name = "eccentricity", nullable = false)
    Double eccentricity;

    @Column(name = "apocenter")
    Double apocenter;

    @Column(name = "pericenter")
    Double pericenter;
}
