package org.klimashin.ga.first.solution.domain.model.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoOptimalSolutionException extends RuntimeException {

    double bestApproach;

    public NoOptimalSolutionException(String message, double bestApproach) {
        super(message);
        this.bestApproach = bestApproach;
    }
}
