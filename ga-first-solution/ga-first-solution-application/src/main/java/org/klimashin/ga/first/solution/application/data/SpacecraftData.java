package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpacecraftData {

    UUID id;
    Double mass;
    Point position;
    Vector speed;
    Vector acceleration;
    Double dryMass;
    Double fuelMass;
    EngineData engine;
    Integer engineCount;
}
