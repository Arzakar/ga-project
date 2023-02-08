package org.klimashin.ga.math.domain.neuron.function;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IdentityFunction implements ActivationFunction {

    private double ratio = 1;

    @Override
    public double apply(double value) {
        return ratio * value;
    }
}
