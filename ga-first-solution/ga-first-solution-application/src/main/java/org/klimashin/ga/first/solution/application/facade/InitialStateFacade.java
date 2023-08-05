package org.klimashin.ga.first.solution.application.facade;

import org.klimashin.ga.first.solution.api.dto.InitialStateGeneratorRequestDtoV1;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.service.InitialStateService;
import org.klimashin.ga.first.solution.application.service.SpacecraftInitialStateService;
import org.klimashin.ga.first.solution.application.service.SpacecraftService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitialStateFacade {

    InitialStateService initialStateService;
    SpacecraftService spacecraftService;
    SpacecraftInitialStateService spacecraftInitialStateService;
    ThreadPoolTaskExecutor taskExecutor;

    public void generateInitialStates(InitialStateGeneratorRequestDtoV1 parameters) {
        if (taskExecutor.getActiveCount() > 0) {
            throw new RuntimeException("Too early");
        }

        taskExecutor.execute(initialStateGenerator(parameters));
    }

    private Runnable initialStateGenerator(InitialStateGeneratorRequestDtoV1 parameters) {
        return () -> {
            var spacecraft = spacecraftService.getSpacecraft(parameters.getSpacecraftId());
            var spacecraftInitialState = spacecraftInitialStateService.getSpacecraftInitialState(parameters.getSpacecraftInitialStateId());

            var durationBounds = parameters.getDurationBounds();
            var durationBoundStep = parameters.getDurationBoundStep();
            var durationList = Stream.concat(
                            LongStream.iterate(durationBounds.getLeft(), i -> i < durationBounds.getRight(), i -> i + durationBoundStep).boxed(),
                            LongStream.of(durationBounds.getRight()).boxed())
                    .sorted(Long::compareTo)
                    .toList();

            var deviationBounds = parameters.getDeviationBounds();
            var deviationBoundStep = parameters.getDeviationBoundStep();
            var deviationList = Stream.concat(
                            DoubleStream.iterate(deviationBounds.getLeft(), i -> i < deviationBounds.getRight(), i -> i + deviationBoundStep).boxed(),
                            DoubleStream.of(deviationBounds.getRight()).boxed())
                    .sorted(Double::compareTo)
                    .toList();

            log.info("Генерация запущена");

            for (var firstDuration : durationList) {
                for (var firstDeviation : deviationList) {
                    for (var secondDuration : durationList) {
                        for (var secondDeviation : deviationList) {
                            var durations = List.of(firstDuration, secondDuration);
                            var deviations = List.of(firstDeviation, secondDeviation);
                            var initialStateEntity = new InitialStateEntity(spacecraft, spacecraftInitialState, durations, deviations);

                            initialStateService.save(initialStateEntity);
                        }
                    }
                }
            }

            log.info("Генерация завершена");
        };
    }
}
