package org.klimashin.ga.math.domain.rungekutta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RkData {

    double argument;
    double argumentStep;

    double[] variables;

    @Override
    public String toString() {
        var variablesAsString = Arrays.stream(this.getVariables())
                .mapToObj(variable -> String.format("%.6f", variable))
                .collect(Collectors.joining(", "));
        return String.format("RkData(argument=%.6f, argumentStep=%.6f, variables=[%s])",
                this.argument, this.argumentStep, variablesAsString);
    }
}
