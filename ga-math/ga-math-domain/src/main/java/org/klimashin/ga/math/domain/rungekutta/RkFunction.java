package org.klimashin.ga.math.domain.rungekutta;

@FunctionalInterface
public interface RkFunction {

    double apply(double argument, double[] variables);
}
