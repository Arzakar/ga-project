package org.klimashin.ga.first.solution.domain.model;

public record Pair(long startValue, long endValue) {

    public int compareToByStartValue(Pair pair) {
        return Long.compare(this.startValue, pair.startValue);
    }

    public static Pair of(long startValue, long endValue) {
        return new Pair(startValue, endValue);
    }
}
