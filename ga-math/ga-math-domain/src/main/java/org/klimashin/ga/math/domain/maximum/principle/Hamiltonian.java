package org.klimashin.ga.math.domain.maximum.principle;

@FunctionalInterface
public interface Hamiltonian {

    double apply(double[] xVariables, double[] psiVariables, double[] uVariables);
}
