package org.klimashin.ga.first.solution.domain.method;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class FixedPointIterationMethodTest {

    @ParameterizedTest
    @MethodSource("initialGuessAndExpectedValue")
    void getSolution_shouldReturnSolution(double initialGuess, double expectedValue) {
        Function<Double, Double> function = (variable) -> 0.9 * Math.sin(variable) + 0.1;

        var method = new FixedPointIterationMethod(function, 0.001);

        assertThat(method.getSolution(initialGuess))
                .isEqualTo(expectedValue, withPrecision(0.000000001));
    }

    private static Stream<Arguments> initialGuessAndExpectedValue() {
        return Stream.of(
                Arguments.of(0.1, 0.628199454),
                Arguments.of(0.2, 0.628298759),
                Arguments.of(0.3, 0.628525059)
        );
    }
}