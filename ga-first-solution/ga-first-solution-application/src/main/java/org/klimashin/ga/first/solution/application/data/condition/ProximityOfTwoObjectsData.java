package org.klimashin.ga.first.solution.application.data.condition;

import org.klimashin.ga.first.solution.domain.model.PointParticle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProximityOfTwoObjectsData implements TargetStateData {

    PointParticle firstParticle;
    PointParticle secondParticle;
    Double requiredDistance;

    @Override
    @JsonIgnore
    public TargetStateTypeData getType() {
        return TargetStateTypeData.PROXIMITY_OF_TWO_OBJECTS;
    }

    @Override
    @JsonIgnore
    public ProximityOfTwoObjectsData getState() {
        return this;
    }
}
