package org.klimashin.ga.first.solution;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

    public static double round(double value, int precision) {
        var rate = Math.pow(10, precision);
        return Math.round(value * rate) / rate;
    }
}
