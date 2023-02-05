package org.klimashin.ga.math.domain.asserter;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;
import org.klimashin.ga.math.domain.rungekutta.RkData;

@UtilityClass
public class RkDataAsserter {

    public void assertEquals(RkData expected, RkData actual, double delta) {
        Assertions.assertEquals(expected.getArgument(), actual.getArgument(), delta);
        Assertions.assertEquals(expected.getArgumentStep(), actual.getArgumentStep(), delta);

        Assertions.assertEquals(expected.getVariables().length, actual.getVariables().length);
        for(int i = 0; i < expected.getVariables().length; i++) {
            Assertions.assertEquals(expected.getVariables()[i], actual.getVariables()[i], delta);
        }
    }
}
