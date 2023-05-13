package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.CascadeType;
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
@Table(name = "celestial_body")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBodyEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orbit_id")
    OrbitEntity orbit;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "mass", nullable = false)
    Double mass;
}
