package org.klimashin.ga.first.solution.application.configuration;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

import org.klimashin.ga.first.solution.application.data.ModelEnvironmentData;
import org.klimashin.ga.first.solution.domain.model.Pair;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;

public class EasyRandomConfiguration {

    public static EasyRandom defaultEasyRandom() {
        var longRandomizer = new LongRandomizer();

        var parameters = new EasyRandomParameters()
                .randomize(Pair.class, () -> new Pair(longRandomizer.getRandomValue(), longRandomizer.getRandomValue()))
                .excludeField(inClass(ModelEnvironmentData.class).and(named("commandProfile")))
                .excludeField(inClass(ModelEnvironmentData.class).and(named("targetState")));

        return new EasyRandom(parameters);
    }

}
