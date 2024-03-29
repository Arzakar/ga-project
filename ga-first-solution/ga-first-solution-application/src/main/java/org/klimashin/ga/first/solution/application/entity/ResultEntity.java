package org.klimashin.ga.first.solution.application.entity;

import org.klimashin.ga.first.solution.application.entity.converter.DoubleListConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "result")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "initial_state_id", nullable = false)
    InitialStateEntity initialState;

    @Convert(converter = DoubleListConverter.class)
    @Column(name = "spacecraft_pos")
    List<Double> spacecraftPos;

    @Convert(converter = DoubleListConverter.class)
    @Column(name = "earth_pos")
    List<Double> earthPos;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    ResultState state;

    @Column(name = "best_approach")
    Double bestApproach;

    @Column(name = "fail_reason")
    String failReason;

    @CreationTimestamp
    @Column(name = "create_timestamp", nullable = false, updatable = false)
    ZonedDateTime createTimestamp;

    public enum ResultState {

        SUCCESSFUL,
        NOT_OPTIMAL,
        FAILED
    }

    public ResultEntity(InitialStateEntity initialState, Double bestApproach, String failReason) {
        this.initialState = initialState;
        this.state = ResultState.NOT_OPTIMAL;
        this.bestApproach = bestApproach;
        this.failReason = failReason;
    }

    public ResultEntity(InitialStateEntity initialState, ResultState state, String failReason) {
        this.initialState = initialState;
        this.state = state;
        this.failReason = failReason;
    }

    public ResultEntity(InitialStateEntity initialState, List<Double> spacecraftPos, List<Double> earthPos) {
        this.initialState = initialState;
        this.spacecraftPos = spacecraftPos;
        this.earthPos = earthPos;
        this.state = ResultState.SUCCESSFUL;
    }
}
