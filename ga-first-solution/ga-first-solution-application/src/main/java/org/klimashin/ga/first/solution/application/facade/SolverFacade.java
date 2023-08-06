package org.klimashin.ga.first.solution.application.facade;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.entity.ResultEntity;
import org.klimashin.ga.first.solution.application.service.CelestialBodyService;
import org.klimashin.ga.first.solution.application.service.InitialStateService;
import org.klimashin.ga.first.solution.application.service.ResultService;
import org.klimashin.ga.first.solution.domain.ModelEnvironment;
import org.klimashin.ga.first.solution.domain.Modeler;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Orbit;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;
import org.klimashin.ga.first.solution.domain.model.condition.ProximityOfTwoObjects;
import org.klimashin.ga.first.solution.domain.model.exception.NoOptimalSolutionException;
import org.klimashin.ga.first.solution.domain.model.profile.FixedVectorDeviationCommandProfile;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.util.LongPair;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SolverFacade {

    CelestialBodyService celestialBodyService;
    InitialStateService initialStateService;
    ThreadPoolTaskExecutor taskExecutor;
    ResultService resultService;
    PlatformTransactionManager platformTransactionManager;

    public void startResolveProcess() {
        if (taskExecutor.getActiveCount() > 0) {
            throw new RuntimeException("Too early");
        }

        var transactionTemplate = new TransactionTemplate(platformTransactionManager, new DefaultTransactionDefinition(PROPAGATION_REQUIRES_NEW));

        var pageSize = 20;
        var solar = celestialBodyService.getCelestialBody("Solar");
        var earth = celestialBodyService.getCelestialBody("Earth");
        var newTaskExecutor = Executors.newFixedThreadPool(6);
        var completionService = new ExecutorCompletionService<ResultEntity>(newTaskExecutor);

        taskExecutor.execute(() -> {
            var initialStates = Optional.ofNullable(transactionTemplate.execute(state -> getPagedInitialStates(pageSize)))
                    .orElseThrow();

            while (!initialStates.isEmpty()) {
                var tasks = initialStates.stream()
                        .map(initialState -> (Callable<ResultEntity>) () -> {
                            var centralBody = new PointParticle(solar.getMass(), Point2D.of(0, 0));
                            var earthOrbit = Orbit.builder()
                                    .semiMajorAxis(earth.getSemiMajorAxis())
                                    .eccentricity(earth.getEccentricity())
                                    .trueAnomaly(0)
                                    .build();
                            var earthCelestialBody = CelestialBody.builder()
                                    .name(earth.getName())
                                    .mass(earth.getMass())
                                    .orbit(earthOrbit)
                                    .build();

                            var spacecraftInitialState = initialState.getSpacecraftInitialState();
                            var spacecraft = Optional.of(initialState.getSpacecraft())
                                    .map(entity -> Spacecraft.builder()
                                            .mass(entity.getDryMass() + entity.getStartFuelMass())
                                            .fuelMass(entity.getStartFuelMass())
                                            .position(Point2D.of(spacecraftInitialState.getPosX(), spacecraftInitialState.getPosY()))
                                            .speed(Vector2D.of(spacecraftInitialState.getSpdX(), spacecraftInitialState.getSpdY()))
                                            .engine(Engine.builder()
                                                    .thrust(entity.getEngineThrust())
                                                    .fuelConsumption(entity.getEngineFuelConsumption())
                                                    .build())
                                            .engineCount(entity.getEngineCount())
                                            .build())
                                    .orElseThrow();

                            var targetState = new ProximityOfTwoObjects(earthCelestialBody, spacecraft, Math.pow(10, 9));

                            var intervals = new HashMap<LongPair, Double>();
                            var currentBound = 0L;
                            for (int i = 0; i < initialState.getDurations().size(); i++) {
                                var pair = LongPair.of(currentBound, initialState.getDurations().get(i));
                                var deviation = initialState.getDeviations().get(i);
                                intervals.put(pair, deviation);
                                currentBound += initialState.getDurations().get(i);
                            }
                            var commandProfile = new FixedVectorDeviationCommandProfile(spacecraft, centralBody, intervals);

                            var environment = ModelEnvironment.builder()
                                    .centralBody(centralBody)
                                    .celestialBodies(List.of(earthCelestialBody))
                                    .spacecraft(spacecraft)
                                    .targetState(targetState)
                                    .commandProfile(commandProfile)
                                    .build();
                            try {
                                var modelerResult = new Modeler(environment).execute();
                                var spacecraftPos = modelerResult.getSpacecraft().getPosition();
                                var earthPos = modelerResult.getCelestialBodies().get(0).getPosition();
                                return new ResultEntity(initialState, List.of(spacecraftPos.getX(), spacecraftPos.getY()), List.of(earthPos.getX(), earthPos.getY()));

                            } catch (NoOptimalSolutionException exception) {
                                return new ResultEntity(initialState, exception.getMessage());

                            } catch (Exception exception) {
                                return new ResultEntity(initialState, ResultEntity.ResultState.FAILED, exception.getMessage());
                            }
                        })
                        .toList();

                for (var task : tasks) {
                    completionService.submit(task);
                }
                var results = new ArrayList<ResultEntity>();
                var updatedInitialState = new ArrayList<InitialStateEntity>();

                while (results.size() < initialStates.size()) {
                    try {
                        var result = completionService.take().get();
                        var initialStateStatus = result.getState().equals(ResultEntity.ResultState.SUCCESSFUL)
                                ? InitialStateEntity.InitialStateStatus.RESOLVED
                                : InitialStateEntity.InitialStateStatus.FAILED;

                        results.add(result);
                        updatedInitialState.add(result.getInitialState().setStatus(initialStateStatus));

                    } catch (InterruptedException | ExecutionException exception) {
                        throw new RuntimeException(exception);
                    }
                }

                transactionTemplate.executeWithoutResult(state -> {
                            resultService.saveAll(results);
                            initialStateService.saveAll(updatedInitialState);
                        }
                );

                initialStates = Optional.ofNullable(transactionTemplate.execute(state -> getPagedInitialStates(pageSize)))
                        .orElseThrow();
            }
        });
    }

    public List<InitialStateEntity> getPagedInitialStates(int pageSize) {
        return initialStateService.getPageByStatus(InitialStateEntity.InitialStateStatus.CREATED, 0, pageSize).getContent();
    }
}
