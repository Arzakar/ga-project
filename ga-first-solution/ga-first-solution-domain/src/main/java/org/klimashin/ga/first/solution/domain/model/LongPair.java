package org.klimashin.ga.first.solution.domain.model;

public record LongPair(long leftValue, long rightValue) {

    public int compareToByLeftValue(LongPair longPair) {
        return Long.compare(this.leftValue, longPair.leftValue);
    }

    public static LongPair of(long leftValue, long rightValue) {
        return new LongPair(leftValue, rightValue);
    }
}
