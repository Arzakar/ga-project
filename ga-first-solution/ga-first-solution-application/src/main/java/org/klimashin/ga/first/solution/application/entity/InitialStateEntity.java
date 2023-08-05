package org.klimashin.ga.first.solution.application.entity;

import org.klimashin.ga.first.solution.application.entity.converter.DoubleListConverter;
import org.klimashin.ga.first.solution.application.entity.converter.LongListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "initial_state")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InitialStateEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "spacecraft_id", nullable = false)
    SpacecraftEntity spacecraft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "spacecraft_initial_state_id", nullable = false)
    SpacecraftInitialStateEntity spacecraftInitialState;

    @Convert(converter = LongListConverter.class)
    @Column(name = "durations", nullable = false)
    List<Long> durations;

    @Convert(converter = DoubleListConverter.class)
    @Column(name = "deviations", nullable = false)
    List<Double> deviations;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    InitialStateStatus status = InitialStateStatus.CREATED;

    @CreationTimestamp
    @Column(name = "create_timestamp", nullable = false, updatable = false)
    ZonedDateTime createTimestamp;

    public enum InitialStateStatus {

        CREATED,
        PROCESSING,
        RESOLVED,
        FAILED
    }

    public InitialStateEntity(SpacecraftEntity spacecraft,
                              SpacecraftInitialStateEntity spacecraftInitialState,
                              List<Long> durations,
                              List<Double> deviations) {
        this.spacecraft = spacecraft;
        this.spacecraftInitialState = spacecraftInitialState;
        this.durations = durations;
        this.deviations = deviations;
    }
}
