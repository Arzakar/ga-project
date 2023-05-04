package org.klimashin.ga.first.solution.domain.model.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.math.Vector;
import org.klimashin.ga.first.solution.domain.model.PointParticle;
import org.klimashin.ga.first.solution.domain.util.Vectors;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FixedVectorDeviationCommandProfile implements CommandProfile {

    PointParticle startVectorObject;
    PointParticle endVectorObject;
    Map<Pair, Double> intervals;
    List<Pair> timeBounds;

    public FixedVectorDeviationCommandProfile(PointParticle startVectorObject,
                                              PointParticle endVectorObject,
                                              Map<Pair, Double> intervals) {
        this.startVectorObject = startVectorObject;
        this.endVectorObject = endVectorObject;
        this.intervals = intervals;
        this.timeBounds = this.intervals.keySet().stream()
                .sorted(Pair::compareToByStartValue)
                .toList();

        validateTimeBounds(timeBounds);
    }

    public Vector getThrustForceDirection(final long currentTime) {
        var lastPair = timeBounds.get(timeBounds.size() - 1);
        var angle = timeBounds.stream()
                .filter(pair -> currentTime >= pair.startValue() && currentTime < pair.endValue())
                .findFirst()
                .or(() -> currentTime >= lastPair.endValue() ? Optional.of(lastPair) : Optional.empty())
                .map(intervals::get)
                .orElseThrow(() -> new RuntimeException(String.format("""
                        В таблице временных интервалов управления отсутствует интервал,
                        содержащий текущее время, равное %d
                        """, currentTime)));

        return Vectors.between(startVectorObject, endVectorObject)
                .toUnit()
                .rotateByZ(angle);
    }

    public void validateTimeBounds(List<Pair> timeBounds) {
        for (int i = 0; i < timeBounds.size() - 1; i++) {
            var leftBound = timeBounds.get(i);
            var rightBound = timeBounds.get(i + 1);

            if (leftBound.endValue() > rightBound.startValue()) {
                throw new IllegalArgumentException(String.format("""
                        Ошибка в таблице временных интервалов управления.
                        Конечная граница интервала %d равна %d и больше начальной границы
                        интервала %d, которая равна %d.
                        """, i, leftBound.endValue(), i + 1, rightBound.startValue()));
            }
        }
    }
}
