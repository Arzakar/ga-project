package org.klimashin.ga.first.solution.application.configuration;

import org.klimashin.ga.first.solution.domain.model.Pair;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;

public class EasyRandomConfiguration {

    public static EasyRandom defaultEasyRandom() {
        var longRandomizer = new LongRandomizer();

        var parameters = new EasyRandomParameters()
                .randomize(Pair.class, () -> new Pair(longRandomizer.getRandomValue(), longRandomizer.getRandomValue()));

        return new EasyRandom(parameters);
    }

}
