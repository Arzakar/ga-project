package org.klimashin.ga.first.solution.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PairTest {

    @ParameterizedTest
    @CsvSource({"2,-1", "0,0", "-2,1"})
    void compareToByLeftValue_shouldCompare(long leftValue, int expectedResult) {
        var pair = new Pair(0, 5);
        var comparedPair = new Pair(leftValue, 0);

        var result = pair.compareToByLeftValue(comparedPair);

        assertThat(result).isEqualTo(expectedResult);

        assertThat(pair)
                .returns(0L, Pair::leftValue)
                .returns(5L, Pair::rightValue);

        assertThat(comparedPair)
                .returns(leftValue, Pair::leftValue)
                .returns(0L, Pair::rightValue);
    }

    @Test
    void of_shouldCreatePair() {
        var result = Pair.of(2, 3);

        assertThat(result)
                .returns(2L, Pair::leftValue)
                .returns(3L, Pair::rightValue);
    }
}