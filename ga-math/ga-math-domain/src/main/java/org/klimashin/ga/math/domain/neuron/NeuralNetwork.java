package org.klimashin.ga.math.domain.neuron;

import org.klimashin.ga.math.domain.neuron.function.BentIdentity;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    public void start() {

        List<Layer> layers = new ArrayList<>();
        layers.add(new InputLayer(5));
        layers.add(new HiddenLayer(7, 5, new BentIdentity()));
        layers.add(new HiddenLayer(7, 7, new BentIdentity()));
        layers.add(new OutputLayer(5, 7, new BentIdentity()));
    }
}
