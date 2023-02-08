package org.klimashin.ga.math.domain.neuron.function;

@FunctionalInterface
public interface ActivationFunction {

    double apply(double value);
}
