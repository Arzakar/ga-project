package org.klimashin.ga.first.solution.domain.model.profile;

import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;
import org.klimashin.ga.first.solution.util.math.util.Vectors;
import org.klimashin.ga.first.solution.util.util.ComparablePair;
import org.klimashin.ga.first.solution.util.util.LongPair;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FixedVectorDeviationCommandProfile implements CommandProfile {

    PointParticle startVectorObject;
    PointParticle endVectorObject;
    SortedMap<LongPair, Double> intervals = new TreeMap<>(ComparablePair::compareByLeftTo);

    public FixedVectorDeviationCommandProfile(PointParticle startVectorObject,
                                              PointParticle endVectorObject,
                                              Map<LongPair, Double> intervals) {
        this.startVectorObject = startVectorObject;
        this.endVectorObject = endVectorObject;
        this.intervals.putAll(intervals);

        validateIntervals(intervals);
    }

    @Override
    public Vector2D getThrustForceDirection(final long currentTime) {
        var angle = intervals.keySet().stream()
                .filter(pair -> currentTime >= pair.getLeft() && currentTime < pair.getRight())
                .findFirst()
                .or(() -> currentTime >= intervals.lastKey().getRight()
                        ? Optional.of(intervals.lastKey())
                        : Optional.empty())
                .map(intervals::get)
                .orElseThrow(() -> new RuntimeException(String.format("""
                        В таблице временных интервалов управления отсутствует интервал,
                        содержащий текущее время, равное %d
                        """, currentTime)));

        return Vectors.between(startVectorObject.getPosition(), endVectorObject.getPosition())
                .toUnit()
                .rotate(angle);
    }

    private void validateIntervals(Map<LongPair, Double> intervals) {
        var pairs = intervals.keySet().stream()
                .sorted(LongPair::compareByLeftTo)
                .toList();

        for (int i = 0; i < pairs.size() - 1; i++) {
            var leftBound = pairs.get(i);
            var rightBound = pairs.get(i + 1);

            if (leftBound.getRight() > rightBound.getLeft()) {
                throw new IllegalArgumentException(String.format("""
                        Ошибка в таблице временных интервалов управления.
                        Конечная граница интервала %d равна %d и больше начальной границы
                        интервала %d, которая равна %d.
                        """, i, leftBound.getRight(), i + 1, rightBound.getLeft()));
            }
        }
    }
}
