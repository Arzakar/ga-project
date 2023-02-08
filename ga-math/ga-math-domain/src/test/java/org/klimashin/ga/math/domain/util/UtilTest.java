package org.klimashin.ga.math.domain.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void connectArrays_shouldConnectArrays() {
        var firstArray = new double[]{1, 6, 7, 2};
        var secondArray = new double[]{6, 2, 9, 0};
        var thirdArray = new double[]{8, 2, 1, 7};

        var expectedResult = new double[]{1, 6, 7, 2, 6, 2, 9, 0, 8, 2, 1, 7};
        var actualResult = Util.connectArrays(firstArray, secondArray, thirdArray);

        assertArrayEquals(expectedResult, actualResult);
    }
}