package org.klimashin.ga.modeler.dim2.dictionary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CelestialBodyName {

    EARTH("Земля"),
    SOLAR("Солнце");

    @Getter
    String name;
}
