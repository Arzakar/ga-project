package org.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InitialStateGeneratorRequestDtoV1 {

    String spacecraftId;
    String spacecraftInitialStateId;
    LongPair durationBounds;
    Long durationBoundStep;
    DoublePair deviationBounds;
    Double deviationBoundStep;

    @Data
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class LongPair {

        Long left;
        Long right;
    }

    @Data
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DoublePair {

        Double left;
        Double right;
    }
}

