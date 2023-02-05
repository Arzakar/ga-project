package org.klimashin.ga.math.domain.rungekutta;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RkSolver {

    public RkData calculateRkStep(final RkFunction[] functions, final RkData incomingRkData) {
        var count = functions.length;
        if (count != incomingRkData.getVariables().length) {
            throw new IllegalStateException("Количество функций должно быть равно количеству неизвестных в системе");
        }

        var argument = incomingRkData.getArgument();
        var argumentStep = incomingRkData.getArgumentStep();

        var incomingVariables = incomingRkData.getVariables();
        var supportVariables = new double[incomingVariables.length];

        var kArgs = new double[count][4];

        for(int i = 0; i < count; i++) {
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

        return new RkData()
                .setArgument(argument + argumentStep)
                .setArgumentStep(argumentStep)
                .setVariables(resultVariables);
    }
}
