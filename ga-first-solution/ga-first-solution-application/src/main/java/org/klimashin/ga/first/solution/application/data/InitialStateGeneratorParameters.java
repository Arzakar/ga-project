package org.klimashin.ga.first.solution.application.data;

import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.util.DoublePair;
import org.klimashin.ga.first.solution.util.util.LongPair;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateGeneratorParameters {

    UUID centralBodyId;
    List<InitialCelestialBodyParameters> celestialBodies;
    InitialSpacecraftParameters spacecraft;
    LongPair durationBounds;
    Long durationBoundStep;
    DoublePair deviationBounds;
    Double deviationBoundStep;

    @Data
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class InitialCelestialBodyParameters {

        UUID id;
        Double trueAnomaly;
    }

    @Data
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class  InitialSpacecraftParameters {

        UUID id;
        Point2D spacecraftPosition;
        Vector2D spacecraftSpeed;
    }
}
