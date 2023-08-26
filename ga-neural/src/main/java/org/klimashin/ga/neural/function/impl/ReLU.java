package org.klimashin.ga.neural.function.impl;

import org.klimashin.ga.neural.function.ActivationFunction;

public class ReLU implements ActivationFunction {

    @Override
    public double activate(double weightedSum) {
        return Math.max(0, weightedSum);
    }
}
