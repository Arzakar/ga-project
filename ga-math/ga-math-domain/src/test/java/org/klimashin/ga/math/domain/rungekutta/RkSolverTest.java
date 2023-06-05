package org.klimashin.ga.math.domain.rungekutta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.klimashin.ga.math.domain.asserter.RkDataAsserter;

import java.util.stream.Stream;

class RkSolverTest {

    @ParameterizedTest
    @MethodSource("functionsAndRkData")
    void calculateRkStep_shouldReturnCorrectRkData(RkFunction[] functions,
                                                   RkStepData incomingRkStepData,
                                                   RkStepData expectedData) {
        var rkSolver = new RkSolver();
        var resultRkData = rkSolver.calculateRkStep(functions, incomingRkStepData);

        RkDataAsserter.assertEquals(expectedData, resultRkData, 0.000001);
    }

    private static Stream<Arguments> functionsAndRkData() {
        RkFunction function01 = (argument, variables) -> -2 * argument * variables[0];

        return Stream.of(
                Arguments.of(new RkFunction[]{function01},
                        new RkStepData(0, 0.2, new double[]{2}),
                        new RkStepData(0.2, 0.2, new double[]{1.921579}))
        );
    }

//    @Test
//    void test() {
//        var rkSolver = new RkSolver();
//        rkSolver.resolveSystem();
//    }
}