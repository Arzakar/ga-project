package com.klimashin.ga.first.solution.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionDtoV1 {

    Double x;
    Double y;
    Double z;
}
