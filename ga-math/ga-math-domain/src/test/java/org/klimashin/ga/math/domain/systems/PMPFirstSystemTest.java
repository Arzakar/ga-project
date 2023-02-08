package org.klimashin.ga.math.domain.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PMPFirstSystemTest {

    private EasyRandom easyRandom = new EasyRandom();
    private PMPFirstSystem system;

    private final double delta = 0.000001;

    private double[] xVars = new double[]{10, 7, 15, 27, 60, 4.2, 1, -9, 86};
    private double[] psiVars = new double[]{82, 4, 9.1, 4, -72, 2, 19, 2, 10};
    private double[] uVars = new double[]{5, 1};

    private double[] vars = new double[]{
            10, 7, 15, 27, 60, 4.2, 1, -9, 86,
            82, 4, 9.1, 4, -72, 2, 19, 2, 10,
            5, 1
    };

    @BeforeEach
    void setup() {
        system = new PMPFirstSystem(9, 11, 50, 13);
    }

    @Test
    void xFunctionsTest() {
        var arg = easyRandom.nextDouble();
        assertEquals(15, system.getX1Function().apply(arg, vars), delta);
        assertEquals(27, system.getX2Function().apply(arg, vars), delta);
        assertEquals(-0.192904, system.getX3Function().apply(arg, vars), delta);
        assertEquals(0.079562, system.getX4Function().apply(arg, vars), delta);
        assertEquals(-50, system.getX5Function().apply(arg, vars), delta);
        assertEquals(-9, system.getX6Function().apply(arg, vars), delta);
        assertEquals(86, system.getX7Function().apply(arg, vars), delta);
        assertEquals(-709.8, system.getX8Function().apply(arg, vars), delta);
        assertEquals(-169, system.getX9Function().apply(arg, vars), delta);
    }

    @Test
    void psiFunctionsTest() {
        var arg = easyRandom.nextDouble();
        assertEquals(-0.149306, system.getPsi1Function().apply(arg, vars), delta);
        assertEquals(0.045049, system.getPsi2Function().apply(arg, vars), delta);
        assertEquals(-82, system.getPsi3Function().apply(arg, vars), delta);
        assertEquals(-4, system.getPsi4Function().apply(arg, vars), delta);
        assertEquals(-0.014139, system.getPsi5Function().apply(arg, vars), delta);
        assertEquals(338, system.getPsi6Function().apply(arg, vars), delta);
        assertEquals(1690, system.getPsi7Function().apply(arg, vars), delta);
        assertEquals(-2, system.getPsi8Function().apply(arg, vars), delta);
        assertEquals(-19, system.getPsi9Function().apply(arg, vars), delta);
    }

    @Test
    void uFunctionsTest() {
        var arg = easyRandom.nextDouble();
        assertEquals(-1.612905, system.getU1Function().apply(arg, vars), delta);
        assertEquals(3598.151678, system.getU2Function().apply(arg, vars), delta);
    }

    @Test
    void hamiltonianTest() {
        assertEquals(3441.962821, system.getHamiltonian().apply(xVars, psiVars, uVars), delta);
    }

    @Test
    void zeroHamiltonianTest() {
        assertFalse(system.getZeroHamiltonian().check(xVars, psiVars, uVars));
    }

    @Test
    void zeroDifByU1FunctionTest() {
        assertFalse(system.getZeroDifByU1Function().check(xVars, psiVars, uVars));
    }

    @Test
    void zeroDifByU2FunctionTest() {
        assertFalse(system.getZeroDifByU2Function().check(xVars, psiVars, uVars));
    }
}