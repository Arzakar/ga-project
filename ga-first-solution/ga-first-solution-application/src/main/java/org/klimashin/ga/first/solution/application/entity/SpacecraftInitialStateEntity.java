package org.klimashin.ga.first.solution.application.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.regex.Pattern;

@Entity
@Table(name = "spacecraft_initial_state")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpacecraftInitialStateEntity {

    @Id
    String id;

    @Column(name = "pos_x", nullable = false)
    Double posX;

    @Column(name = "pos_y", nullable = false)
    Double posY;

    @Column(name = "spd_x", nullable = false)
    Double spdX;

    @Column(name = "spd_y", nullable = false)
    Double spdY;

    public SpacecraftInitialStateEntity(String id, double posX, double posY, double spdX, double spdY) {
        var idIsValid = Pattern.compile("Ang\\d{3}Spd\\d{5}").matcher(id).find();

        if (!idIsValid) {
            throw new RuntimeException("Invalid id format");
        }

        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.spdX = spdX;
        this.spdY = spdY;
    }
}
