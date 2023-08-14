package org.klimashin.ga.modeler.dim2.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Engine {

    double thrust;
    double fuelConsumption;
}
