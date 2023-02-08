package org.klimashin.ga.math.domain.maximum.principle;

@FunctionalInterface
public interface MaximumCondition {

    boolean check(double[] xVariables, double[] psiVariables, double[] uVariables);
}
