package org.klimashin.ga.first.solution.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "initial_state_celestial_body_part")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateCelestialBodyPartEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "initial_state_id", nullable = false)
    InitialStateEntity initialState;

    @OneToOne(optional = false)
    @JoinColumn(name = "celestial_body_id", nullable = false)
    CelestialBodyEntity celestialBody;

    @Column(name = "true_anomaly", nullable = false)
    Double trueAnomaly;
}
