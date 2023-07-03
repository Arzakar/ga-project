package org.klimashin.ga.first.solution.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongPairTest {

    @ParameterizedTest
    @CsvSource({"2,-1", "0,0", "-2,1"})
    void compareToByLeftValue_shouldCompare(long leftValue, int expectedResult) {
        var pair = new LongPair(0, 5);
        var comparedPair = new LongPair(leftValue, 0);

        var result = pair.compareToByLeftValue(comparedPair);

        assertThat(result).isEqualTo(expectedResult);

        assertThat(pair)
                .returns(0L, LongPair::leftValue)
                .returns(5L, LongPair::rightValue);

        assertThat(comparedPair)
                .returns(leftValue, LongPair::leftValue)
                .returns(0L, LongPair::rightValue);
    }

    @Test
    void of_shouldCreatePair() {
        var result = LongPair.of(2, 3);

        assertThat(result)
                .returns(2L, LongPair::leftValue)
                .returns(3L, LongPair::rightValue);
    }
}