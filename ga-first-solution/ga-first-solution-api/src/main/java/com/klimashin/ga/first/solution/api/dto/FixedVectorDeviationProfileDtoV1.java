package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FixedVectorDeviationProfileDtoV1 {

    UUID startVectorObjectId;
    UUID endVectorObjectId;

    List<Range> timeBounds;
    List<Range> deviationAngle;

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Range {

        Long minValue;
        Long maxValue;
        Long step;
    }
}
