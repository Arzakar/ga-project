package org.klimashin.ga.first.solution.domain.model.exception;

public class NoOptimalSolutionException extends RuntimeException {

    public NoOptimalSolutionException(String message) {
        super(message);
    }
}
