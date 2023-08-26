package org.klimashin.ga.neural.function;

@FunctionalInterface
public interface ActivationFunction {

    double activate(double weightedSum);
}
