package org.klimashin.ga.first.solution.application.configuration;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;

public class EasyRandomConfiguration {

    public static EasyRandom defaultEasyRandom() {
        var longRandomizer = new LongRandomizer();

        var parameters = new EasyRandomParameters();

        return new EasyRandom(parameters);
    }

}
