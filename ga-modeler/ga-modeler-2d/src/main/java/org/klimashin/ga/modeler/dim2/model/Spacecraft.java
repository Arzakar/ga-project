package org.klimashin.ga.modeler.dim2.model;

import org.klimashin.ga.lib.math.dim2.domain.Point;
import org.klimashin.ga.lib.math.dim2.domain.Vector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Spacecraft implements MaterialPoint {

    final Point position;
    final Vector speed;

    final Engine engine;
    final int engineCount;

    final double dryMass;

    double fuelMass;

    private Spacecraft(Point position, Vector speed, Engine engine, int engineCount, double dryMass, double fuelMass) {
        this.position = position;
        this.speed = speed;
        this.engine = engine;
        this.engineCount = engineCount;
        this.dryMass = dryMass;
        this.fuelMass = fuelMass;
    }

    @Override
    public double getMass() {
        return dryMass + fuelMass;
    }
}
