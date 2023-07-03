package org.klimashin.ga.first.solution.util.util;

public class LongPair extends ComparablePair<Long, Long> {

    public static LongPair of(Long left, Long right) {
        return new LongPair(left, right);
    }

    protected LongPair(Long left, Long right) {
        super(left, right);
    }
}
