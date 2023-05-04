package org.klimashin.ga.first.solution.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.klimashin.ga.first.solution.domain.math.Vector;

@Data
@SuperBuilder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Spacecraft extends PointParticle {

    Vector speed;
    Vector acceleration;

    double dryMass;
    double fuelMass;

    Engine engine;
    int engineCount;

    @Override
    public double getMass() {
        return dryMass + fuelMass;
    }

    public Spacecraft changePosition(Vector force, double deltaTime) {
        acceleration = force.copy().multiply(getMass());
        speed.add(acceleration.copy().multiply(deltaTime));
        position.move(speed.copy().multiply(deltaTime));
        return this;
    }

    public double getEngineSystemThrust() {
        return engine.getThrust() * engineCount;
    }

    public void reduceFuel(double deltaTime) {
        var deltaMass = (engine.getFuelConsumption() * engineCount) * deltaTime;
        fuelMass -= deltaMass;
        mass -= deltaMass;
    }
}
