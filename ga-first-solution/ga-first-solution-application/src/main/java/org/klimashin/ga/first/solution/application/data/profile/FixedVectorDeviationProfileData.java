package org.klimashin.ga.first.solution.application.data.profile;

import org.klimashin.ga.first.solution.domain.model.LongPair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FixedVectorDeviationProfileData implements CommandProfileData {

    UUID startVectorObjectId;
    UUID endVectorObjectId;
    Map<LongPair, Double> intervals;
    List<LongPair> timeBounds;

    @Override
    @JsonIgnore
    public CommandProfileTypeData getType() {
        return CommandProfileTypeData.FIXED_VECTOR_DEVIATION;
    }

    @Override
    @JsonIgnore
    public FixedVectorDeviationProfileData getProfile() {
        return this;
    }
}
