package org.klimashin.ga.math.domain.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.Arrays;

@UtilityClass
public class Util {

    public static double squaresSum(double a, double b) {
        return Math.pow(a, 2) + Math.pow(b, 2);
    }

    public static double sqrtOfSquaresSum(double a, double b) {
        return Math.sqrt(squaresSum(a, b));
    }

    public static double[] connectArrays(double[]... arrays) {
        int generalLength = Arrays.stream(arrays).mapToInt(Array::getLength).sum();
        double[] resultArray = new double[generalLength];

        int iterateVariable = 0;
        for(double[] array : arrays) {
            for (double element : array) {
                resultArray[iterateVariable] = element;
                iterateVariable++;
            }
        }

        return resultArray;
    }
}
