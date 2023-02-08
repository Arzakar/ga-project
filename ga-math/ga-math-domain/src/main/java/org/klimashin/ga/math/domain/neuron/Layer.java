package org.klimashin.ga.math.domain.neuron;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.klimashin.ga.math.domain.neuron.function.ActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Layer {

    List<Neuron> neurons;
    ActivationFunction activationFunction;

    public Layer(List<Neuron> neurons, ActivationFunction activationFunction) {
        this.neurons = neurons;
        this.activationFunction = activationFunction;
    }

    public List<Double> compute(List<Double> inputSignals) {
        List<Double> rawOutputs = new ArrayList<>();
        for (Neuron neuron : neurons) {
            double rawOutput = 0;
            for (int i = 0; i < inputSignals.size(); i++) {
                rawOutput += inputSignals.get(i) * neuron.getInputWeights().get(i);
            }
            rawOutputs.add(rawOutput);
        }

        return rawOutputs.stream().map(activationFunction::apply).collect(Collectors.toList());
    }

}
