package org.klimashin.ga.first.solution.domain.model;

import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Spacecraft extends PointParticle {

    final Vector2D speed;
    double fuelMass;
    final Engine engine;
    final int engineCount;

    public static SpacecraftBuilder builder() {
        return new SpacecraftBuilder();
    }

    private Spacecraft(double mass, Point2D position, Vector2D speed,
                       double fuelMass, Engine engine, int engineCount) {
        this.mass = mass;
        this.position = position;
        this.speed = speed;
        this.fuelMass = fuelMass;
        this.engine = engine;
        this.engineCount = engineCount;
    }

    public Spacecraft move(Vector2D force, double deltaTime) {
        var acceleration = force.copy().divide(mass);

        speed.add(acceleration.copy().multiply(deltaTime));
        position.move(speed.copy().multiply(deltaTime));

        return this;
    }

    public double getEngineSystemThrust() {
        return engine.getThrust() * engineCount;
    }

    public boolean isEnoughFuel(double deltaTime) {
        var requiredFuelMass = (engine.getFuelConsumption() * engineCount) * deltaTime;
        return fuelMass >= requiredFuelMass;
    }

    public Spacecraft reduceFuel(double deltaTime) {
        var deltaMass = (engine.getFuelConsumption() * engineCount) * deltaTime;
        fuelMass -= deltaMass;
        mass -= deltaMass;

        return this;
    }

    @Setter
    @Accessors(chain = true, fluent = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SpacecraftBuilder {

        double mass;
        Point2D position;
        Vector2D speed;
        double fuelMass;
        Engine engine;
        int engineCount;

        public Spacecraft build() {
            if (mass <= this.fuelMass) {
                throw new IllegalArgumentException("Масса топлива не может быть больше или равной массе КА");
            }

            return new Spacecraft(mass, position, speed, fuelMass, engine, engineCount);
        }
    }
}
