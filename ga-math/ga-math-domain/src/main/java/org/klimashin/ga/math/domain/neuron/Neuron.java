package org.klimashin.ga.math.domain.neuron;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Neuron {

    List<Double> inputWeights = new ArrayList<>();
    Random random = new SecureRandom();

    public Neuron(int inputWeightsCount) {
        IntStream.range(0, inputWeightsCount).forEach($ -> inputWeights.add(random.nextDouble(1)));
    }

    public void init(int inputWeightsCount) {
        IntStream.range(0, inputWeightsCount).forEach($ -> inputWeights.add(random.nextDouble(1)));
    }
}
