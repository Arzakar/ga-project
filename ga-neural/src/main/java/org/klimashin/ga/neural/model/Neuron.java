package org.klimashin.ga.neural.model;

import org.klimashin.ga.neural.function.ActivationFunction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Neuron {

    final ActivationFunction activationFunction;

    @Getter
    double value;

    public Neuron(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public double activate(double weightedSum) {
        value = activationFunction.activate(weightedSum);
        return value;
    }
}
