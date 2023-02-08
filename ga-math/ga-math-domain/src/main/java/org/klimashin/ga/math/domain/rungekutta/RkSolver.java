package org.klimashin.ga.math.domain.rungekutta;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

@Log
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RkSolver {

    public void resolveSystem(RkSystemData rkSystemData) {
        RkStepData rkStepData = new RkStepData()
                .setArgument(rkSystemData.getStartArgument())
                .setArgumentStep(rkSystemData.getArgumentStep())
                .setVariables(rkSystemData.getVariablesByStartArgument());
        log.info(rkStepData.toString());

        while (rkStepData.getArgument() < rkSystemData.getFinishArgument()) {
            rkStepData = calculateRkStep(rkSystemData.getRkFunctions(), rkStepData);
            log.info(rkStepData.toString());
        }
    }

    public RkStepData calculateRkStep(final RkFunction[] functions, final RkStepData incomingRkStepData) {
        var count = functions.length;
        if (count != incomingRkStepData.getVariables().length) {
            throw new IllegalStateException("Количество функций должно быть равно количеству неизвестных в системе");
        }

        var argument = incomingRkStepData.getArgument();
        var argumentStep = incomingRkStepData.getArgumentStep();

        var incomingVariables = incomingRkStepData.getVariables();
        var supportVariables = new double[incomingVariables.length];

        var kArgs = new double[count][4];

        for (int i = 0; i < count; i++) {
            kArgs[i][0] = functions[i].apply(argument, incomingVariables);
            supportVariables[i] = incomingVariables[i] + argumentStep * kArgs[i][0] / 2;
        }

        for (int i = 0; i < count; i++) {
            kArgs[i][1] = functions[i].apply(argument + argumentStep / 2, supportVariables);
            supportVariables[i] = incomingVariables[i] + argumentStep * kArgs[i][1] / 2;
        }

        for (int i = 0; i < count; i++) {
            kArgs[i][2] = functions[i].apply(argument + argumentStep / 2, supportVariables);
            supportVariables[i] = incomingVariables[i] + argumentStep * kArgs[i][2];
        }

        for (int i = 0; i < count; i++) {
            kArgs[i][3] = functions[i].apply(argument + argumentStep, supportVariables);
        }

        double[] resultVariables = new double[count];
        for (int i = 0; i < count; i++) {
            resultVariables[i] = incomingVariables[i] + (argumentStep / 6) * (kArgs[i][0] + 2 * kArgs[i][1] + 2 * kArgs[i][2] + kArgs[i][3]);
        }

        return new RkStepData()
                .setArgument(argument + argumentStep)
                .setArgumentStep(argumentStep)
                .setVariables(resultVariables);
    }
}
