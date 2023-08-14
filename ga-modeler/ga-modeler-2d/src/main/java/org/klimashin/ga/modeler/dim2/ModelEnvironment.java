package org.klimashin.ga.modeler.dim2;

import org.klimashin.ga.modeler.dim2.model.CelestialBody;
import org.klimashin.ga.modeler.dim2.model.Orbit;
import org.klimashin.ga.modeler.dim2.model.Spacecraft;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Map;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelEnvironment {

    CelestialBody centralBody;
    Map<CelestialBody, Orbit> celestialBodies;

    Spacecraft spacecraft;
}
