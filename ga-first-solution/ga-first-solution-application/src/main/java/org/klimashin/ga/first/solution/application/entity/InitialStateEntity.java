package org.klimashin.ga.first.solution.application.entity;

import org.klimashin.ga.first.solution.application.entity.converter.DoubleListConverter;
import org.klimashin.ga.first.solution.application.entity.converter.IntegerListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "initial_state")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "central_body_id", nullable = false)
    CelestialBodyEntity centralBody;

    @OneToOne(optional = false)
    @JoinColumn(name = "spacecraft_id", nullable = false)
    SpacecraftEntity spacecraft;

    @Column(name = "spacecraft_position_x", nullable = false)
    Double spacecraftPositionX;

    @Column(name = "spacecraft_position_y", nullable = false)
    Double spacecraftPositionY;

    @Column(name = "spacecraft_speed_x", nullable = false)
    Double spacecraftSpeedX;

    @Column(name = "spacecraft_speed_y", nullable = false)
    Double spacecraftSpeedY;

    @Convert(converter = IntegerListConverter.class)
    @Column(name = "bounds", nullable = false)
    List<Integer> bounds;

    @Convert(converter = DoubleListConverter.class)
    @Column(name = "deviations", nullable = false)
    List<Double> deviations;

    @CreationTimestamp
    @Column(name = "create_timestamp", nullable = false, updatable = false)
    ZonedDateTime createTimestamp;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "initialState")
    List<InitialStateCelestialBodyPartEntity> celestialBodies;
}
