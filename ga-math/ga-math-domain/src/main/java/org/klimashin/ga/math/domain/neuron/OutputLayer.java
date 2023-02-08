package org.klimashin.ga.math.domain.neuron;

import static java.util.stream.Collectors.toList;

import org.klimashin.ga.math.domain.neuron.function.ActivationFunction;

import java.util.stream.IntStream;

public class OutputLayer extends Layer {

    public OutputLayer(int neuronsCount, int previousLayerNeuronsCount, ActivationFunction activationFunction) {
        super(IntStream.range(0, neuronsCount)
                        .mapToObj($ -> new Neuron(previousLayerNeuronsCount))
                        .collect(toList()),
                activationFunction);
    }
}
