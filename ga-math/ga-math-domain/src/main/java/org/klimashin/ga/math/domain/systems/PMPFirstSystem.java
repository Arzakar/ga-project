package org.klimashin.ga.math.domain.systems;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static org.klimashin.ga.math.domain.PhysicConstant.EARTH_ANGULAR_SPEED;
import static org.klimashin.ga.math.domain.PhysicConstant.SOLAR_GRAVITY_PARAMETER;
import static org.klimashin.ga.math.domain.util.Util.connectArrays;
import static org.klimashin.ga.math.domain.util.Util.sqrtOfSquaresSum;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.klimashin.ga.math.domain.maximum.principle.Hamiltonian;
import org.klimashin.ga.math.domain.maximum.principle.MaximumCondition;
import org.klimashin.ga.math.domain.rungekutta.RkFunction;
import org.klimashin.ga.math.domain.util.Util;

import java.util.stream.DoubleStream;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PMPFirstSystem {

    public static final double X_VARS_LENGTH = 9;
    public static final double PSI_VARS_LENGTH = 9;
    public static final double U_VARS_LENGTH = 2;

    double muSolar = SOLAR_GRAVITY_PARAMETER;
    double engF;
    double gamma;
    double omega = EARTH_ANGULAR_SPEED;

    final RkFunction x1Function = (arg, vars) -> vars[2];
    final RkFunction x2Function = (arg, vars) -> vars[3];
    final RkFunction x3Function = (arg, vars) -> {
        double firstTerm = -muSolar * (vars[0] / pow(sqrtOfSquaresSum(vars[0], vars[1]), 3));
        double secondTerm = -(engF / vars[4])
                * ((vars[0] * Math.cos(vars[18]) - vars[1] * sin(vars[18])) / (sqrtOfSquaresSum(vars[0], vars[1])))
                * vars[19];
        return firstTerm + secondTerm;
    };
    final RkFunction x4Function = (arg, vars) -> {
        double firstTerm = -muSolar * (vars[1] / pow(sqrtOfSquaresSum(vars[0], vars[1]), 3));
        double secondTerm = -(engF / vars[4])
                * ((vars[0] * sin(vars[18]) + vars[1] * Math.cos(vars[18])) / (sqrtOfSquaresSum(vars[0], vars[1])))
                * vars[19];
        return firstTerm + secondTerm;
    };
    final RkFunction x5Function = (arg, vars) -> -gamma * vars[19];
    final RkFunction x6Function = (arg, vars) -> vars[7];
    final RkFunction x7Function = (arg, vars) -> vars[8];
    final RkFunction x8Function = (arg, vars) -> -pow(omega, 2) * vars[5];
    final RkFunction x9Function = (arg, vars) -> -pow(omega, 2) * vars[6];

    final RkFunction psi1Function = (arg, vars) -> {
        double firstTerm = (muSolar * (vars[11] * pow(vars[1], 2) - 2 * vars[11] * pow(vars[0], 2) - 3 * vars[12] * vars[0] * vars[1]))
                / (pow(Util.squaresSum(vars[0], vars[1]), 2) * sqrtOfSquaresSum(vars[0], vars[1]));
        double secondTerm = (engF * vars[19] * vars[1])
                * ((vars[11] * vars[1] - vars[12] * vars[0]) * cos(vars[18]) + (vars[11] * vars[0] + vars[12] * vars[1]) * sin(vars[18]))
                / (vars[4] * Util.squaresSum(vars[0], vars[1]) * sqrtOfSquaresSum(vars[0], vars[1]));

        return firstTerm + secondTerm;
    };
    final RkFunction psi2Function = (arg, vars) -> {
        double firstTerm = (muSolar * (vars[12] * pow(vars[0], 2) - 2 * vars[12] * pow(vars[1], 2) - 3 * vars[11] * vars[0] * vars[1]))
                / (pow(Util.squaresSum(vars[0], vars[1]), 2) * sqrtOfSquaresSum(vars[0], vars[1]));
        double secondTerm = -(engF * vars[19] * vars[0])
                * ((vars[11] * vars[1] - vars[12] * vars[0]) * cos(vars[18]) + (vars[11] * vars[0] + vars[12] * vars[1]) * sin(vars[18]))
                / (vars[4] * Util.squaresSum(vars[0], vars[1]) * sqrtOfSquaresSum(vars[0], vars[1]));

        return firstTerm + secondTerm;
    };
    final RkFunction psi3Function = (arg, vars) -> -vars[9];
    final RkFunction psi4Function = (arg, vars) -> -vars[10];
    final RkFunction psi5Function = (arg, vars) -> -(engF * vars[19])
            * ((vars[11] * vars[0] + vars[12] * vars[1]) * cos(vars[18]) + (vars[12] * vars[0] - vars[11] * vars[1]) * sin(vars[18]))
            / (pow(vars[4], 2) * sqrtOfSquaresSum(vars[0], vars[1]));
    final RkFunction psi6Function = (arg, vars) -> pow(omega, 2) * vars[16];
    final RkFunction psi7Function = (arg, vars) -> pow(omega, 2) * vars[17];
    final RkFunction psi8Function = (arg, vars) -> -vars[14];
    final RkFunction psi9Function = (arg, vars) -> -vars[15];

    final RkFunction u1Function = (arg, vars) -> (engF * vars[19])
            * ((vars[11] * vars[1] - vars[12] * vars[0]) * cos(vars[18]) + (vars[11] * vars[0] + vars[12] * vars[1]) * sin(vars[18]))
            / (vars[4] * sqrtOfSquaresSum(vars[0], vars[1]));
    final RkFunction u2Function = (arg, vars) -> -gamma * vars[13] - 1 - engF
            * ((vars[11] * vars[0] + vars[12] * vars[1]) * cos(vars[18]) + (vars[12] * vars[0] - vars[11] * vars[1]) * sin(vars[18]))
            / (vars[4] * sqrtOfSquaresSum(vars[0], vars[1]));

    final Hamiltonian hamiltonian = (xVars, psiVars, uVars) -> {
        val term01 = -uVars[1];
        val term02 = psiVars[0] * xVars[2];
        val term03 = psiVars[1] * xVars[3];
        val term04 = -psiVars[2] * muSolar * (xVars[0] / pow(sqrtOfSquaresSum(xVars[0], xVars[1]), 3));
        val term05 = -psiVars[2] * (engF / xVars[4])
                * ((xVars[0] * cos(uVars[0]) - xVars[1] * sin(uVars[0])) / (sqrtOfSquaresSum(xVars[0], xVars[1])))
                * uVars[1];
        val term06 = -psiVars[3] * muSolar * (xVars[1] / pow(sqrtOfSquaresSum(xVars[0], xVars[1]), 3));
        val term07 = -psiVars[3] * (engF / xVars[4])
                * ((xVars[0] * sin(uVars[0]) + xVars[1] * Math.cos(uVars[0])) / (sqrtOfSquaresSum(xVars[0], xVars[1])))
                * uVars[1];
        val term08 = -psiVars[4] * gamma * uVars[1];
        val term09 = psiVars[5] * xVars[7];
        val term10 = psiVars[6] * xVars[8];
        val term11 = -psiVars[7] * pow(omega, 2) * xVars[5];
        val term12 = -psiVars[8] * pow(omega, 2) * xVars[6];

        return DoubleStream.of(term01, term02, term03, term04, term05, term06, term07, term08, term09, term10, term11, term12).sum();
    };

    final MaximumCondition zeroHamiltonian = (xVars, psiVars, uVars) -> abs(this.hamiltonian.apply(xVars, psiVars, uVars)) < 0.000001;
    final MaximumCondition zeroDifByU1Function = (xVars, psiVars, uVars) -> {
        var compositeArgs = connectArrays(xVars, psiVars, uVars);
        return abs(this.u1Function.apply(0, compositeArgs)) < 0.000001;
    };
    final MaximumCondition zeroDifByU2Function = (xVars, psiVars, uVars) -> {
        var compositeArgs = connectArrays(xVars, psiVars, uVars);
        return abs(this.u2Function.apply(0, compositeArgs)) < 0.000001;
    };

    final RkFunction[] rkSystemFunctions = new RkFunction[] {
            x1Function, x2Function, x3Function,
            x4Function, x5Function, x6Function,
            x7Function, x8Function, x9Function,
            psi1Function, psi2Function, psi3Function,
            psi4Function, psi5Function, psi6Function,
            psi7Function, psi8Function, psi9Function,
    };

    final RkFunction[] controlFunctions = new RkFunction[] {
            u1Function, u1Function
    };

    final MaximumCondition[] maximumConditions = new MaximumCondition[] {
            zeroHamiltonian, zeroDifByU1Function, zeroDifByU2Function
    };

    public PMPFirstSystem(double muSolar, double engF, double gamma, double omega) {
        this.muSolar = muSolar;
        this.engF = engF;
        this.gamma = gamma;
        this.omega = omega;
    }

    public PMPFirstSystem(double engF, double gamma) {
        this.engF = engF;
        this.gamma = gamma;
    }
}
