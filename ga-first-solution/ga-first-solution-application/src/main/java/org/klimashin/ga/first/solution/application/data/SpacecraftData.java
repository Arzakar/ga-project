package org.klimashin.ga.first.solution.application.data;

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
    PointData position;
    VectorData speed;
    VectorData acceleration;
    Double dryMass;
    Double fuelMass;
    EngineData engine;
    Integer engineCount;
}
