package org.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FixedDeviationCommandProfileRequestDtoV1 {

    UUID startVectorObjectId;
    UUID endVectorObjectId;
    List<Interval> intervals;

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Interval {

        Long leftValue;
        Long rightValue;
        Double deviation;
    }
}
