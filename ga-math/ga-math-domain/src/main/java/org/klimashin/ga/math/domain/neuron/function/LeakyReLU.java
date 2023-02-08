package org.klimashin.ga.math.domain.neuron.function;

public class LeakyReLU implements ActivationFunction {

    @Override
    public double apply(double value) {
        return value < 0 ? 0.01 * value : value;
    }
}
