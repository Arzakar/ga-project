package org.klimashin.ga.first.solution.application;

import org.klimashin.ga.first.solution.domain.model.Pair;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class MainMain {

    public static void main(String[] args) throws InterruptedException {
        var leftBoundForDuration = 10;
        var rightBoundForDuration = 180;
        var durationStep = 10;

        var leftBoundForDeviation = -180;
        var rightBoundForDeviation = 180;
        var deviationStep = 10;

        var durationRange = LongStream.concat(
                LongStream.iterate(leftBoundForDuration, value -> value <= rightBoundForDuration, value -> value + durationStep),
                LongStream.of(rightBoundForDuration)
        ).distinct().sorted().toArray();
        var deviationRange = DoubleStream.concat(
                DoubleStream.iterate(leftBoundForDeviation, value -> value <= rightBoundForDeviation, value -> value + deviationStep),
                DoubleStream.of(rightBoundForDuration)
        ).distinct().sorted().toArray();

        Pair<Long, Double>[] sectorVariations = Arrays.stream(durationRange)
                .mapToObj(duration -> Arrays.stream(deviationRange)
                        .mapToObj(deviation -> new Pair<>(duration, deviation))
                        .toArray((IntFunction<Pair<Long, Double>[]>) Pair[]::new))
                .flatMap(Arrays::stream)
                .toArray((IntFunction<Pair<Long, Double>[]>) Pair[]::new);

        AtomicInteger i = new AtomicInteger();

        for (Pair<Long, Double> sector : sectorVariations) {
            Arrays.stream(sectorVariations)
                    .forEach(secondSector -> {
                        System.out.println(sector + " - " + secondSector);
//                        i.getAndIncrement();
                    });
            i.getAndIncrement();
        }

        System.out.println(i.get());

    }
}
