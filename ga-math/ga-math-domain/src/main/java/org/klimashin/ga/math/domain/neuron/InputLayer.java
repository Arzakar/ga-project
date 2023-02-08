package org.klimashin.ga.math.domain.neuron;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public class InputLayer extends Layer {

    public InputLayer(int neuronsCount) {
        super(IntStream.range(0, neuronsCount).mapToObj($ -> new Neuron()).collect(toList()), null);
    }

    @Override
    public List<Double> compute(List<Double> inputSignals) {
        return inputSignals;
    }
}
