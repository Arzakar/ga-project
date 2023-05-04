package org.klimashin.ga.first.solution.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Engine {

    double thrust;
    double fuelConsumption;
}
