package org.klimashin.ga.math.domain.systems;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.klimashin.ga.math.domain.rungekutta.RkFunction;
import org.klimashin.ga.math.domain.rungekutta.RkSystemData;

import static org.klimashin.ga.math.domain.PhysicConstant.SOLAR_GRAVITY_PARAMETER;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PontryaginMaximumPrinciple extends RkSystemData {

    public PontryaginMaximumPrinciple(double startArgument,
                                      double finishArgument,
                                      double argumentStep,
                                      double[] variablesByStartArgument) {
        super(startArgument, finishArgument, argumentStep, variablesByStartArgument, generateFunctions());
    }

    private static RkFunction[] generateFunctions() {
        RkFunction x1Function = (argument, variables) -> variables[3 - 1];
        RkFunction x2Function = (argument, variables) -> variables[4 - 1];

        return new RkFunction[]{x1Function, x2Function};
    }
}
