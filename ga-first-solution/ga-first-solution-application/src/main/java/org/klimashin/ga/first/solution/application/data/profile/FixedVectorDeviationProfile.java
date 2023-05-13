package org.klimashin.ga.first.solution.application.data.profile;

import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FixedVectorDeviationProfile implements CommandProfileData {

    PointParticle startVectorObject;
    PointParticle endVectorObject;
    Map<Pair, Double> intervals;
    List<Pair> timeBounds;

    @Override
    @JsonIgnore
    public CommandProfileTypeData getType() {
        return CommandProfileTypeData.FIXED_VECTOR_DEVIATION;
    }

    @Override
    @JsonIgnore
    public FixedVectorDeviationProfile getProfile() {
        return this;
    }
}
