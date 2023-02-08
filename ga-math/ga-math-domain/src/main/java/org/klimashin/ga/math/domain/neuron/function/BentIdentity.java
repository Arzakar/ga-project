package org.klimashin.ga.math.domain.neuron.function;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BentIdentity implements ActivationFunction {

    @Override
    public double apply(double value) {
        return ((sqrt(pow(value, 2) + 1) - 1) / 2) + value;
    }
}
