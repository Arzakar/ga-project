package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;

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
    Point2D position;
    Vector2D speed;
    Double mass;
    Double fuelMass;
    Double dryMass;
    Double startFuelMass;
    EngineData engine;
    Integer engineCount;
}
