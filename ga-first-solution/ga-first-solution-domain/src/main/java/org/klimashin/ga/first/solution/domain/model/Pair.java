package org.klimashin.ga.first.solution.domain.model;

public record Pair(long leftValue, long rightValue) {

    public int compareToByLeftValue(Pair pair) {
        return Long.compare(this.leftValue, pair.leftValue);
    }

    public static Pair of(long leftValue, long rightValue) {
        return new Pair(leftValue, rightValue);
    }
}
