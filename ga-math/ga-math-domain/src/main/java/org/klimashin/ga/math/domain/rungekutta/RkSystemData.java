package org.klimashin.ga.math.domain.rungekutta;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class RkSystemData {

    double startArgument;
    double finishArgument;
    double argumentStep;

    double[] variablesByStartArgument;

    RkFunction[] rkFunctions;

    protected RkSystemData(double startArgument, double finishArgument, double argumentStep, double[] variablesByStartArgument, RkFunction[] rkFunctions) {
        validateArguments(startArgument, finishArgument, argumentStep);
        validateStartVariables(variablesByStartArgument, rkFunctions);

        this.startArgument = startArgument;
        this.finishArgument = finishArgument;
        this.argumentStep = argumentStep;
        this.variablesByStartArgument = variablesByStartArgument;
        this.rkFunctions = rkFunctions;
    }

    private void validateStartVariables(double[] variablesByStartArgument, RkFunction[] rkFunctions) {
        if (Objects.isNull(variablesByStartArgument) || Objects.isNull(rkFunctions)) {
            throw new IllegalStateException("Начальные значения переменных и список функций не должны быть null");
        }

        if (variablesByStartArgument.length == 0 || rkFunctions.length == 0) {
            throw new IllegalStateException("Начальные значения переменных и список функций не должны быть пустыми");
        }

        if (variablesByStartArgument.length != rkFunctions.length) {
            throw new IllegalStateException("Количество начальных значений переменных должно быть равно количеству функций");
        }
    }

    private void validateArguments(double startArgument, double finishArgument, double argumentStep) {
        if (startArgument == finishArgument) {
            throw new IllegalStateException("Начальное значение аргумента не должно быть равно конечному");
        }

        if (argumentStep == 0) {
            throw new IllegalStateException("Шаг аргумента не должен быть равен нулю");
        }

        if (startArgument > finishArgument && argumentStep > 0) {
            throw new IllegalStateException("Если начальное значение аргумента больше конечного, то шаг должен быть отрицательным");
        }

        if (startArgument < finishArgument && argumentStep < 0) {
            throw new IllegalStateException("Если начальное значение аргумента меньше конечного, то шаг должен быть положительным");
        }
    }
}
